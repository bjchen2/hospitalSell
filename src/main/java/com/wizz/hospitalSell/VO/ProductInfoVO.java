package com.wizz.hospitalSell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 * Created By Cx On 2018/8/1 21:33
 */
@Data
public class ProductInfoVO {

    //商品Id
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品描述
    private String productDescription;
    //卖家电话
    private String sellerPhone;
    //商品图片url
    private String productIcon;
    //商品总评价
    private Integer productScore;
    //商品总销量
    private Integer productSales;
}
