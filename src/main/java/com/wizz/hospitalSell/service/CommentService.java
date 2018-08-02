package com.wizz.hospitalSell.service;

import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.ProductCommentDto;

import java.util.List;

/**
 * 评价有关
 * Created By Cx On 2018/8/2 9:49
 */
public interface CommentService {

    /**
     * 通过商品ID查询商品所有评论
     */
    List<CommentInfo> findInfosByProductId(String productId);

    /**
     * 查询所有商品评论相关信息
     */
    List<ProductCommentDto> findAllDtos();

    /**
     * 创建新评论
     */
    CommentInfo create(CommentInfo productInfo);
}
