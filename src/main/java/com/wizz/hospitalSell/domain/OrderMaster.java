package com.wizz.hospitalSell.domain;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单概要表
 * Created By Cx On 2018/6/10 15:00
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class OrderMaster {
    //订单id
    @Id
    private String orderId;
    //买家手机号
    private String userPhone;
    //买家姓名
    private String userName;
    //买家地址
    private String userAddress;
    //买家微信openid
    private String userOpenid;
    //订单状态，0:新下单 1：已结单 2：已取消
    private Integer orderStatus;
    //支付状态，默认0:未支付 1：支付成功
    private Integer payStatus;
    //评论状态，默认0:未评论 1：已评论
    private Integer commentStatus;
    //订单总金额
    private BigDecimal orderAmount;
    //配送时间
    private Date deliveryTime;
    //创建时间,默认为当前时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//timestamp用什么来表示。
    //最近修改时间，修改时自动更新
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
