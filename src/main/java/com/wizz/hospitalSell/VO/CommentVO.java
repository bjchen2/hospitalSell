package com.wizz.hospitalSell.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wizz.hospitalSell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 商品评价
 * Created By Cx On 2018/8/1 21:38
 */
@Data
public class CommentVO {

    //商品质量评价
    private Integer qualityScore;
    //商品口味评价
    private Integer tasteScore;
    //商品包装评价
    private Integer packingScore;
    //评价人名称
    private String userName;
    //评价时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
}
