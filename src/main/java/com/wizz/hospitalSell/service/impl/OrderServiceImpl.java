package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created By Cx On 2018/8/1 18:55
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public OrderDto create(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String openId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
