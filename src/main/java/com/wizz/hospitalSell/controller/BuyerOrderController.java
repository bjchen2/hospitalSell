package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.converter.OrderForm2DtoConverter;
import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.form.OrderForm;
import com.wizz.hospitalSell.service.OrderService;
import com.wizz.hospitalSell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 买家端订单
 * Created By Cx On 2018/8/3 22:42
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    OrderService orderService;

    //创建订单
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVO create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //表单校验有误
            log.error("[创建订单]参数不正确，orderForm={}", orderForm);
            throw new SellException(bindingResult.getFieldError().getDefaultMessage(), ResultEnum.PARAM_ERROR.getCode());
        }
        OrderDto orderDto = OrderForm2DtoConverter.convert(orderForm);
        //创建订单
        orderDto = orderService.create(orderDto);
        return ResultUtil.success(orderDto.getOrderId());
    }

    /**
     * 查询订单
     */
    @GetMapping("/{openid}")
    public ResultVO findByOpenid(@PathVariable String openid) {
        return ResultUtil.success(orderService.findByOpenid(openid));
    }

    @PostMapping("/pay")
    public ResultVO pay(@RequestBody Map<String, String> data) {
        String openid = data.get("userOpenid");
        String orderId = data.get("orderId");
        orderService.pay(openid, orderId);
        return ResultUtil.success();
    }

}
