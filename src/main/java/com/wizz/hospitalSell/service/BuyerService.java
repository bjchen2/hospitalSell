package com.wizz.hospitalSell.service;


import com.wizz.hospitalSell.dto.OrderDto;

/**
 * 买家端订单有关（微信端操作）
 * TODO 不用写，后期迭代再加
 * Created By Cx On 2018/6/15 19:24
 */
public interface BuyerService {

    /**
     * 通过openid和orderId查询一个订单
     * openid用于校验该订单是否为该用户，避免恶意查询
     * @return
     */
    OrderDto findOneByOpenidAndOrderId(String openid, String orderId);

    /**
     * 通过openid和orderId取消订单
     * openid用于校验该订单是否为该用户，避免恶意取消
     * @return
     */
    OrderDto cancelOneByOpenidAndOrderId(String openid, String orderId);
}
