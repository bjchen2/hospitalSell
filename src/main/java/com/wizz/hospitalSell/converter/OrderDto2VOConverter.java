package com.wizz.hospitalSell.converter;

import com.wizz.hospitalSell.VO.OrderMasterVO;
import com.wizz.hospitalSell.domain.OrderMaster;
import com.wizz.hospitalSell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/6 9:23
 */
public class OrderDto2VOConverter {

    public static OrderMasterVO convert(OrderDto orderDto){
        if (orderDto == null) return null;
        OrderMasterVO orderMasterVO = new OrderMasterVO();
        BeanUtils.copyProperties(orderDto,orderMasterVO);
        orderMasterVO.setOrderDetailList(OrderDetail2VOConverter.convert(orderDto.getOrderDetailList()));
        return orderMasterVO;
    }

    public static List<OrderMasterVO> convert(List<OrderDto> orderDtos){
        if (orderDtos == null) return null;
        return orderDtos.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
