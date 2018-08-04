package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.OrderDetail;
import com.wizz.hospitalSell.domain.OrderMaster;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.CartDto;
import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.enums.OrderStatusEnum;
import com.wizz.hospitalSell.enums.PayStatusEnum;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.repository.OrderDetailRepository;
import com.wizz.hospitalSell.repository.OrderMasterRepository;
import com.wizz.hospitalSell.service.OrderService;
import com.wizz.hospitalSell.service.ProductInfoService;
import com.wizz.hospitalSell.service.WebSocket;
import com.wizz.hospitalSell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/1 18:55
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    WebSocket webSocket;

    //TODO productInfoService未写好，待测试
    @Override
    public OrderDto create(OrderDto orderDto) {
        //随机生成订单Id
        String orderId = KeyUtil.genUniqueKey();
        orderDto.setOrderId(orderId);
        //购物车
        List<CartDto> cartDtos = new ArrayList<>();
        //订单总价
        BigDecimal amount = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            //查询商品（数量，价格）
            ProductInfo p = productInfoService.findOne(orderDetail.getProductId());
            if (p == null) {
                //若商品不存在,抛商品不存在异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //将商品加入购物车
            cartDtos.add(new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity()));
            //将orderDetail信息补全
            BeanUtils.copyProperties(p, orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            //加上每件商品总价
            amount = amount.add(orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
        }
        //将订单详情添加到数据库
        orderDetailRepository.saveAll(orderDto.getOrderDetailList());
        //将订单概要添加到数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        //虽然已经设置动态插入注解（但仅在字段为空时生效），所以必须手动设置防止数据被篡改
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(amount);
        //存储订单概要，并将createTime返回
        orderDto.setCreateTime(orderMasterRepository.save(orderMaster).getCreateTime());
        //扣库存
        productInfoService.decreaseStock(cartDtos);
        //webSocket发送消息，告知卖家有新订单
        webSocket.sendMessage(orderId);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
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

}
