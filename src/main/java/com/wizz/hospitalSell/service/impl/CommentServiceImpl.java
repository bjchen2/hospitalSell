package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.VO.CommentDetailVO;
import com.wizz.hospitalSell.VO.CommentVO;
import com.wizz.hospitalSell.dao.CommentInfoDao;
import com.wizz.hospitalSell.dao.CommentInfoRepository;
import com.wizz.hospitalSell.dao.ProductInfoDao;
import com.wizz.hospitalSell.dao.UserInfoDao;
import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.dto.ProductCommentDto;
import com.wizz.hospitalSell.enums.PayStatusEnum;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.service.CommentService;
import com.wizz.hospitalSell.service.OrderService;
import com.wizz.hospitalSell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created By Cx On 2018/8/2 9:49
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentInfoRepository commentInfoRepository;
    @Autowired
    ProductInfoDao productInfoDao;
    @Autowired
    CommentInfoDao commentInfoDao;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    OrderService orderService;

    List<ProductCommentDto> findDtosByProductInfos(List<ProductInfo> productInfos) {
        List<ProductCommentDto> productCommentDtos = new ArrayList<>();
        for (ProductInfo productInfo : productInfos) {
            //star为总星数，num为总评价数，result=star/num
            ProductCommentDto productCommentDto = new ProductCommentDto();
            //设置productCommentDto属性
            productCommentDto.setProductId(productInfo.getProductId());
            productCommentDto.setProductName(productInfo.getProductName());
            Map<String, Object> m = commentInfoDao.findScoreMapByProductId(productInfo.getProductId());
            productCommentDto.setPackingScore((Map<String, Integer>) m.get("packingScore"));
            productCommentDto.setQualityScore((Map<String, Integer>) m.get("qualityScore"));
            productCommentDto.setTasteScore((Map<String, Integer>) m.get("tasteScore"));
            productCommentDto.setResult((Double) m.get("result"));
            //添加进列表
            productCommentDtos.add(productCommentDto);
        }
        return productCommentDtos;
    }

    @Override
    @Cacheable(cacheNames = "comment", key = "#productId", unless = "#result == null ")
    public List<CommentVO> findInfosByProductId(String productId) {
        List<CommentInfo> commentInfos = commentInfoRepository.findAllByProductIdOrderByCreateTimeDesc(productId);
        List<CommentVO> commentVOs = new ArrayList<>();
        for (CommentInfo commentInfo : commentInfos) {
            CommentVO commentVO = new CommentVO();
            //通过openid查询用户信息
            UserInfo userInfo = userInfoDao.findByUserOpenid(commentInfo.getUserOpenid());
            if (userInfo == null) {
                //如果某条评论的用户信息缺失，直接跳过，即不添加该评论
                continue;
            }
            //将用户信息赋值给VO
            BeanUtils.copyProperties(userInfo, commentVO);
            //将评论信息赋值给VO，注意：这里赋值顺序不能换，因为他们有一个同名属性createTime
            BeanUtils.copyProperties(commentInfo, commentVO);
            //添加进返回列表
            commentVOs.add(commentVO);
        }
        return commentVOs;
    }

    @Override
    public List<CommentDetailVO> findByOrderId(String orderId) {
        List<CommentInfo> commentInfos = commentInfoRepository.findAllByOrderId(orderId);
        if (commentInfos.isEmpty()) {
            log.error("[获取评论]该订单暂无评论，orderId={}", orderId);
        }
        List<CommentDetailVO> commentDetailVOs = new ArrayList<>();
        for (CommentInfo commentInfo : commentInfos) {
            CommentDetailVO commentDetailVO = new CommentDetailVO();
            ProductInfo productInfo = productInfoDao.getOne(commentInfo.getProductId());
            //将评论信息赋值给VO
            BeanUtils.copyProperties(commentInfo, commentDetailVO);
            //将商品信息赋值给VO
            commentDetailVO.setProductIcon(productInfo.getProductIcon());
            commentDetailVO.setProductName(productInfo.getProductName());
            //添加进返回列表
            commentDetailVOs.add(commentDetailVO);
        }
        return commentDetailVOs;
    }

    @Override
    @Cacheable(cacheNames = "comment", key = "0", unless = "#result == null ")
    public List<ProductCommentDto> findAllDtos() {
        List<ProductInfo> productInfos = productInfoDao.findAll();
        return findDtosByProductInfos(productInfos);
    }

    @Override
    public List<ProductCommentDto> findDtosByProductName(String productName) {
        //使用模糊查询
        productName = "%".concat(productName).concat("%");
        List<ProductInfo> productInfos = productInfoDao.findByProductNameLike(productName);
        return findDtosByProductInfos(productInfos);
    }

    //cache注解的方法要被重载不能private
    @Caching(evict = {
            @CacheEvict(cacheNames = "comment", key = "0"),
            @CacheEvict(cacheNames = "comment", key = "#commentInfo.productId")})
    public void preCreate(CommentInfo commentInfo) {
        if (!productInfoDao.existsById(commentInfo.getProductId())) {
            log.error("[商品评价]商品不存在，productId={}", commentInfo.getProductId());
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //设置评论主键
        commentInfo.setCommentId(KeyUtil.genUniqueKey());
    }

    public void createAll(List<CommentInfo> commentInfos){
        String orderId = commentInfos.get(0).getOrderId();
        //查询订单是否支付
        OrderDto orderDto = orderService.findOne(orderId);
        if (!orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            log.error("[商品评价]该订单未支付，不能评价，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        for (CommentInfo commentInfo : commentInfos) {
            preCreate(commentInfo);
        }
        //保存所有评论
        commentInfoRepository.saveAll(commentInfos);
        //将订单的评论状态设为已评论
        orderService.commented(orderId, commentInfos.get(0).getUserOpenid());
    }

    @Override
    public Integer findResultByProductId(String productId) {
        return commentInfoDao.findResultByProductId(productId);
    }
}
