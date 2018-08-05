package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.VO.CommentVO;
import com.wizz.hospitalSell.dao.CommentInfoDao;
import com.wizz.hospitalSell.dao.CommentInfoRepository;
import com.wizz.hospitalSell.dao.ProductInfoDao;
import com.wizz.hospitalSell.dao.UserInfoDao;
import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.dto.ProductCommentDto;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.service.CommentService;
import com.wizz.hospitalSell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Cx On 2018/8/2 9:49
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentInfoRepository commentInfoRepository;
    @Autowired
    ProductInfoDao productInfoDao;
    @Autowired
    CommentInfoDao commentInfoDao;
    @Autowired
    UserInfoDao userInfoDao;

    public List<ProductCommentDto> findDtosByProductInfos(List<ProductInfo> productInfos){
        List<ProductCommentDto> productCommentDtos = new ArrayList<>();
        for (ProductInfo productInfo : productInfos){
            //star为总星数，num为总评价数，result=star/num
            int star=0,num=0;
            Map<String,Integer> qualityScore = new HashMap<>();
            Map<String,Integer> tasteScore = new HashMap<>();
            Map<String,Integer> packingScore = new HashMap<>();
            ProductCommentDto productCommentDto = new ProductCommentDto();
            //设置productCommentDto属性
            productCommentDto.setProductId(productInfo.getProductId());
            productCommentDto.setProductName(productInfo.getProductName());
            for (int i = 1; i < 6; i++){
                int temp = commentInfoDao.countOfQualityScoreByProductId(productInfo.getProductId(),i);
                star += i*temp; num += temp;
                qualityScore.put(String.valueOf(i),temp);
                temp = commentInfoDao.countOfTasteScoreByProductId(productInfo.getProductId(),i);
                star += i*temp; num += temp;
                tasteScore.put(String.valueOf(i),temp);
                temp = commentInfoDao.countOfPackingScoreByProductId(productInfo.getProductId(),i);
                star += i*temp; num += temp;
                packingScore.put(String.valueOf(i),temp);
            }
            productCommentDto.setPackingScore(packingScore);
            productCommentDto.setQualityScore(qualityScore);
            productCommentDto.setTasteScore(tasteScore);
            productCommentDto.setResult(num==0?0:star*1.0/num);
            //添加进列表
            productCommentDtos.add(productCommentDto);
        }
        return productCommentDtos;
    }

    @Override
    public List<CommentVO> findInfosByProductId(String productId) {
        List<CommentInfo> commentInfos = commentInfoRepository.findAllByProductIdOrderByCreateTime(productId);
        List<CommentVO> commentVOs = new ArrayList<>();
        for (CommentInfo commentInfo : commentInfos){
            CommentVO commentVO = new CommentVO();
            //通过openid查询用户信息
            UserInfo userInfo = userInfoDao.findByUserOpenid(commentInfo.getUserOpenid());
            //将评论信息赋值给VO
            BeanUtils.copyProperties(commentInfo,commentVO);
            //将用户信息赋值给VO
            BeanUtils.copyProperties(userInfo,commentVO);
            //添加进返回列表
            commentVOs.add(commentVO);
        }
        return commentVOs;
    }

    @Override
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

    @Override
    public CommentInfo create(CommentInfo commentInfo) {
        if (!productInfoDao.existsById(commentInfo.getProductId())){
            log.error("[商品评价]商品不存在，productId={}",commentInfo.getProductId());
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //设置主键
        commentInfo.setCommentId(KeyUtil.genUniqueKey());
        return commentInfoRepository.save(commentInfo);
    }
}
