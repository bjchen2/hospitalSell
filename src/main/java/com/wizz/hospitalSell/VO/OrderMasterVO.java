package com.wizz.hospitalSell.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wizz.hospitalSell.enums.CommentStatusEnum;
import com.wizz.hospitalSell.enums.OrderStatusEnum;
import com.wizz.hospitalSell.enums.PayStatusEnum;
import com.wizz.hospitalSell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单主表
 * Created By Cx On 2018/8/3 9:30
 */
@Data
public class OrderMasterVO {

    //订单主表id
    private String orderId;
    //买家手机号
    private String userPhone;
    //买家姓名
    private String userName;
    //买家地址
    private String userAddress;
    //买家微信openId
    private String userOpenid;
    //订单状态，默认0，新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    //支付状态，默认0，未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //评论状态，默认0:未评论 1：已评论
    private Integer commentStatus = CommentStatusEnum.NO_COMMENT.getCode();
    //订单总额
    private BigDecimal orderAmount;
    //配送时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date deliveryTime;
    //创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //该订单商品详情
    private List<OrderDetailVO> orderDetailList;
}
