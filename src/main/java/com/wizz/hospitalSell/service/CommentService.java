package com.wizz.hospitalSell.service;

import com.wizz.hospitalSell.VO.CommentDetailVO;
import com.wizz.hospitalSell.VO.CommentVO;
import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.dto.ProductCommentDto;

import java.util.List;

/**
 * 评价有关
 * Created By Cx On 2018/8/2 9:49
 */
public interface CommentService {

    /**
     * 通过商品ID查询商品所有评论  买家端
     */
    List<CommentVO> findInfosByProductId(String productId);

    /**
     * 通过订单ID查询该订单的所有评论  买家端
     */
    List<CommentDetailVO> findByOrderId(String orderId);

    /**
     * 查询所有商品评论相关信息    卖家端
     */
    List<ProductCommentDto> findAllDtos();

    /**
     * 通过模糊搜索，查询合适的商品评价    卖家端
     */
    List<ProductCommentDto> findDtosByProductName(String productName);

    /**
     * 创建新评论,此时需要将订单的评论状态改为已评论
     */
    CommentInfo create(CommentInfo commentInfo);

    /**
     * 通过productId，查询某商品额总评分
     */
    Integer findResultByProductId(String productId);
}
