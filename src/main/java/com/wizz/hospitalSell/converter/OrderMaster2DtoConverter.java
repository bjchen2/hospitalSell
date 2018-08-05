package com.wizz.hospitalSell.converter;

import com.wizz.hospitalSell.domain.OrderMaster;
import com.wizz.hospitalSell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/5 13:22
 */
public class OrderMaster2DtoConverter {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMaster){
        return orderMaster.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
