package com.wizz.hospitalSell.service;

import com.wizz.hospitalSell.VO.OrderMasterVO;
import com.wizz.hospitalSell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单概要有关，卖家端（PC端后台管理）
 * Created By Cx On 2018/6/11 10:58
 */
public interface OrderService {

    /**
     * 创建订单概要（即下单）
     *
     * @return
     */
    OrderDto create(OrderDto orderDto);

    /**
     * 通过订单id查询某个订单概要(包含订单详情，即商品信息)
     * @return
     */
    OrderDto findOne(String orderId);

    /**
     * 查询所有用户的所有订单概要，分页按时间排序返回(不包含订单详情，即商品信息)
     */
    Page<OrderDto> findList(Pageable pageable);

    /**
     * 取消订单
     *
     * @return
     */
    OrderDto cancel(OrderDto orderDto);

    /**
     * 完成订单（即已接单）
     *
     * @return
     */
    OrderDto finish(OrderDto orderDto);


    /**
     * 通过openid查询某用户的订单   ——买家端
     */
    List<OrderMasterVO> findByOpenid(String openid);

    /**
     * 订单支付,openid用于验证该订单是否为该用户的 ——买家端
     */
    void pay(String openid,String productId);
}
