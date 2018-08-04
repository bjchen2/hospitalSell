package com.wizz.hospitalSell.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单详情
 * Created By Cx On 2018/8/3 9:28
 */
@Data
public class OrderDetailVO {

    //订单详情id
    private String detailId;
    //商品id
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品数量
    private Integer productQuantity;
    //商品图片url
    private String productIcon;
    //卖家联系电话
    private String sellerPhone;

}
