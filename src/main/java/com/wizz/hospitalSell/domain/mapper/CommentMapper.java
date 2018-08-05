package com.wizz.hospitalSell.domain.mapper;

import org.apache.ibatis.annotations.*;

/**
 * Created By Cx On 2018/8/5 15:41
 */
public interface CommentMapper {

    /**
     * 查询某商品口味评价为score的条数
     */
    @Select("select count(taste_score) from comment_info where taste_score=#{score} and product_id=#{productId}")
    int countOfTasteScoreByProductId(@Param("productId") String productId, @Param("score") int score);

    /**
     * 查询某商品包装评价为score的条数
     */
    @Select("select count(packing_score) from comment_info where taste_score=#{score} and product_id=#{productId}")
    int countOfPackingScoreByProductId(@Param("productId") String productId, @Param("score") int score);

    /**
     * 查询某商品质量评价为score的条数
     */
    @Select("select count(quality_score) from comment_info where taste_score=#{score} and product_id=#{productId}")
    int countOfQualityScoreByProductId(@Param("productId") String productId, @Param("score") int score);

}
