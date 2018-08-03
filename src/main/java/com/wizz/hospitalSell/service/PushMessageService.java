package com.wizz.hospitalSell.service;

import com.wizz.hospitalSell.dto.OrderDto;

/**
 * 消息推送service
 * Created By Cx On 2018/8/3 21:25
 */
public interface PushMessageService {

    /**
     * 订单已接单推送
     */
    void orderFinish(OrderDto orderDto);
}
