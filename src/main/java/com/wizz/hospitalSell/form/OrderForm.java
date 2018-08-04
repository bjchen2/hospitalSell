package com.wizz.hospitalSell.form;

import com.wizz.hospitalSell.domain.OrderDetail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 订单表单参数验证类
 * Created By Cx On 2018/6/15 10:36
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String userName;

    /**
     * 买家电话
     */
    @NotBlank(message = "电话不能为空")
    private String userPhone;

    /**
     * 买家地址
     */
    @NotBlank(message = "地址不能为空")
    private String userAddress;

    /**
     * 买家微信openid
     */
    @NotBlank(message = "微信openid不能为空")
    private String userOpenid;

    /**
     * 配送时间时间戳，单位：毫秒
     */
    @NotNull(message = "配送时间不能为空")
    private Date deliveryTime;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车商品不能为空")
    private List<OrderDetail> items;

}
