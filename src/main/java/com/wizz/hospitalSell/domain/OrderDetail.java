package com.wizz.hospitalSell.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 * Created By Cx On 2018/6/10 15:06
 */
@Entity
@DynamicInsert
@Data
public class OrderDetail {
    //订单详情id
    @Id
    private String detailId;
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名称
    private String productName;
    //商品单价
    private BigDecimal productPrice;//用什么数据类型来进行存储不确定？double够么？
    //商品数量
    private Integer productQuantity;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
