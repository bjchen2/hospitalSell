package com.wizz.hospitalSell.domain.mapper;

import com.wizz.hospitalSell.domain.CommentInfo;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    @Select("select * from comment_info where product_id=#{productId}")
    @Results({
            @Result(column = "quality_score" , property = "qualityScore"),
            @Result(column = "taste_score" , property = "tasteScore"),
            @Result(column = "packing_score" , property = "packingScore"),
    })
    List<CommentInfo> findScoreByProductId(String productId);

    /**
     * 查询某个商品的总评分
     */
    @Select("select (SUM(quality_score)+SUM(taste_score)+SUM(packing_score))/(3*count(*)) from comment_info where product_id = #{productId}")
    Double findResultByProductId(String productId);
}
