package com.wizz.hospitalSell.converter;

import com.wizz.hospitalSell.VO.OrderDetailVO;
import com.wizz.hospitalSell.domain.OrderDetail;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/6 13:01
 */
public class OrderDetail2VOConverter {

    public static OrderDetailVO convert(OrderDetail orderDetail) {
        if (orderDetail == null) return null;
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderDetail, orderDetailVO);
        return orderDetailVO;
    }

    public static List<OrderDetailVO> convert(List<OrderDetail> orderDetails) {
        if (orderDetails == null) return null;
        return orderDetails.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
