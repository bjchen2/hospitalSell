package com.wizz.hospitalSell.VO;

import com.wizz.hospitalSell.enums.OrderStatusEnum;
import com.wizz.hospitalSell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
    //订单总额
    private BigDecimal orderAmount;
    //创建时间
    private Date createTime;
}
