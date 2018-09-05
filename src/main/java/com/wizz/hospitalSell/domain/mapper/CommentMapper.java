package com.wizz.hospitalSell.domain.mapper;

import com.wizz.hospitalSell.domain.CommentInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created By Cx On 2018/8/5 15:41
 */
public interface CommentMapper {

    @Select("select * from comment_info where product_id=#{productId}")
    @Results({
            @Result(column = "quality_score", property = "qualityScore"),
            @Result(column = "taste_score", property = "tasteScore"),
            @Result(column = "packing_score", property = "packingScore"),
    })
    List<CommentInfo> findScoreByProductId(String productId);

    /**
     * 查询某个商品的总评分
     */
    @Select("select (SUM(quality_score)+SUM(taste_score)+SUM(packing_score))/(3*count(*)) from comment_info where product_id = #{productId}")
    Double findResultByProductId(String productId);
}
