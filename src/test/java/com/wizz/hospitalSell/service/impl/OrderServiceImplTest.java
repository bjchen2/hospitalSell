package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.VO.OrderMasterVO;
import com.wizz.hospitalSell.converter.OrderDto2VOConverter;
import com.wizz.hospitalSell.service.OrderService;
import com.wizz.hospitalSell.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/6 9:14
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    public void create() {
    }

    @Test
    public void findOne() {
    }

    @Test
    public void findList() {
    }

    @Test
    public void findList1() {
        Pageable pageable = PageRequest.of(0,10);
        List<OrderMasterVO> orderMasterVOs = OrderDto2VOConverter.convert(orderService.findList(pageable).getContent());
        System.out.println(JsonUtil.toJson(orderMasterVOs));
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }
}