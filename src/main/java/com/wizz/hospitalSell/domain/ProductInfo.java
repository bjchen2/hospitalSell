package com.wizz.hospitalSell.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 类目表
 * Created By Cx On 2018/6/10 14:56
 */
@Entity
@Data
@DynamicUpdate
@DynamicInsert
public class ProductInfo {

    //商品id
    @Id
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;
    //商品总销量
    private Integer productSales;
    //商品简介
    private String productDescription;
    //商品url
    private String productIcon;
    //卖家联系电话
    private String sellerPhone;
    //商品的状态0：上架 1：下架
    private Integer productStatus;
    //商品的类目 0为默认类目
    private Integer categoryType;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //最近新改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
