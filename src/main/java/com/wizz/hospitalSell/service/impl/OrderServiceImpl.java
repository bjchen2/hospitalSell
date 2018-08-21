package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.VO.OrderDetailVO;
import com.wizz.hospitalSell.VO.OrderMasterVO;
import com.wizz.hospitalSell.converter.OrderDetail2VOConverter;
import com.wizz.hospitalSell.converter.OrderMaster2DtoConverter;
import com.wizz.hospitalSell.converter.OrderMaster2VOController;
import com.wizz.hospitalSell.dao.OrderDetailDao;
import com.wizz.hospitalSell.dao.OrderMasterDao;
import com.wizz.hospitalSell.domain.OrderDetail;
import com.wizz.hospitalSell.domain.OrderMaster;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.CartDto;
import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.enums.OrderStatusEnum;
import com.wizz.hospitalSell.enums.PayStatusEnum;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.service.OrderService;
import com.wizz.hospitalSell.service.ProductInfoService;
import com.wizz.hospitalSell.service.WebSocket;
import com.wizz.hospitalSell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created By Cx On 2018/8/1 18:55
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    OrderMasterDao orderMasterDao;
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    WebSocket webSocket;

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
        orderDetailDao.saveAll(orderDto.getOrderDetailList());
        //将订单概要添加到数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        //虽然已经设置动态插入注解（但仅在字段为空时生效），所以必须手动设置防止数据被篡改
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(amount);
        //存储订单概要，并将createTime返回
        orderDto.setCreateTime(orderMasterDao.save(orderMaster).getCreateTime());
        //增加销量
        productInfoService.increaseSales(cartDtos);
        //webSocket发送消息，告知卖家有新订单
        webSocket.sendMessage(orderId);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        Optional<OrderMaster> orderMaster = orderMasterDao.findById(orderId);
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderId);
        if (!orderMaster.isPresent()){
            //若订单概要不存在
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (orderDetails == null){
            //若订单详情不存在
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster.get(),orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAllByOrderByCreateTimeDesc(pageable);
        return new PageImpl<OrderDto>(OrderMaster2DtoConverter.convert(orderMasterPage.getContent()),pageable,
                orderMasterPage.getTotalElements());
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        //判断订单状态是否可取消(只有新下单状态可取消订单)
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()) ){
            log.error("[取消订单]订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态并保存
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        //若已支付，则退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //退款
            //修改订单状态
            orderDto.setPayStatus(PayStatusEnum.WAIT.getCode());
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMasterDao.save(orderMaster);
        //将取消订单的商品返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            //如果商品详情为空，则不需要商品减销量
            return orderDto;
        }
        List<CartDto> cartDtos = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseSales(cartDtos);
        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getPayStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[接订单]订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态并存储
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMasterDao.save(orderMaster);
        return orderDto;
    }

    @Override
    public List<OrderMasterVO> findByOpenid(String openid) {
        List<OrderMaster> orderMasters = orderMasterDao.findByUserOpenid(openid);
        List<OrderMasterVO> orderMasterVOs = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasters){
            List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderMaster.getOrderId());
            List<OrderDetailVO> orderDetailVOs =  OrderDetail2VOConverter.convert(orderDetails);
            OrderMasterVO orderMasterVO = OrderMaster2VOController.convert(orderMaster);
            orderMasterVO.setOrderDetailList(orderDetailVOs);
            orderMasterVOs.add(orderMasterVO);
        }
        return orderMasterVOs;
    }

    @Override
    public void pay(String openid, String orderId) {
        OrderDto orderDto = findOne(orderId);
        if (!openid.equals(orderDto.getUserOpenid())){
            log.error("[支付订单]订单支付失败，该订单不属于该用户，openid={}.orderOpenid={}",openid,orderDto.getUserOpenid());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[支付订单]订单支付失败，订单状态非未支付，payStatus={}",orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMasterDao.save(orderMaster);
    }

}
