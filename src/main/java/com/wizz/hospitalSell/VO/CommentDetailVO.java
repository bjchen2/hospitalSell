package com.wizz.hospitalSell.VO;


import lombok.Data;

/**
 * 用于查看单个订单评论时返回
 * Created By Cx On 2018/9/3 21:10
 */
@Data
public class CommentDetailVO {

    //评论id
    private String commentId;
    //商品质量评价
    private Integer qualityScore;
    //商品口味评价
    private Integer tasteScore;
    //商品包装评价
    private Integer packingScore;
    //商品名称
    private String productName;
    //商品URL
    private String productIcon;

}
