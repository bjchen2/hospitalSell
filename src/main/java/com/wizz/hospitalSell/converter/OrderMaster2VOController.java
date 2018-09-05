package com.wizz.hospitalSell.converter;

import com.wizz.hospitalSell.VO.OrderMasterVO;
import com.wizz.hospitalSell.domain.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/7 14:58
 */
public class OrderMaster2VOController {

    public static OrderMasterVO convert(OrderMaster orderMaster) {
        OrderMasterVO orderMasterVO = new OrderMasterVO();
        BeanUtils.copyProperties(orderMaster, orderMasterVO);
        return orderMasterVO;
    }

    public static List<OrderMasterVO> convert(List<OrderMaster> orderMaster) {
        return orderMaster.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
