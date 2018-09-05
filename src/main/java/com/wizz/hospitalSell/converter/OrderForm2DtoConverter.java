package com.wizz.hospitalSell.converter;

import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.form.OrderForm;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/7 15:20
 */
public class OrderForm2DtoConverter {

    public static OrderDto convert(OrderForm orderForm) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderForm, orderDto);
        orderDto.setOrderDetailList(orderForm.getItems());
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderForm> orderForms) {
        return orderForms.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
