package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.dto.OrderDto;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.form.OrderForm;
import com.wizz.hospitalSell.service.BuyerService;
import com.wizz.hospitalSell.service.OrderService;
import com.wizz.hospitalSell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
//    @Autowired
//    BuyerService buyerService;

    //创建订单
    //TODO 待测试
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVO create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            //表单校验有误
            log.error("[创建订单]参数不正确，orderForm={}",orderForm);
            throw new SellException(bindingResult.getFieldError().getDefaultMessage(), ResultEnum.PARAM_ERROR.getCode());
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderForm,orderDto);
        //创建订单
        orderService.create(orderDto);
        return ResultUtil.success();
    }

}
