package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.form.CommentForm;
import com.wizz.hospitalSell.service.CommentService;
import com.wizz.hospitalSell.service.ProductInfoService;
import com.wizz.hospitalSell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

/**
 * 买家端评价
 * Created By Cx On 2018/8/3 22:42
 */
@RestController
@RequestMapping("/buyer/comment")
@Slf4j
public class BuyerCommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    ProductInfoService productInfoService;

    @GetMapping("/{productId}")
    public ResultVO findByProductId(@PathVariable String productId){
        if (productInfoService.findOne(productId) == null){
            log.error("[商品评价]商品id错误，商品id不存在，productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return ResultUtil.success(commentService.findInfosByProductId(productId));
    }

    //注意，作为接收Json的类（CommentForm），必须要有Get/Set方法
    @PostMapping
    public ResultVO create(@Valid @RequestBody CommentForm commentForm , BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            //表单校验有误
            log.error("[创建订单]参数不正确，commentForm={}",commentForm);
            throw new SellException(bindingResult.getFieldError().getDefaultMessage(), ResultEnum.PARAM_ERROR.getCode());
        }
        CommentInfo commentInfo = new CommentInfo();
        BeanUtils.copyProperties(commentForm,commentInfo);
        commentService.create(commentInfo);
        return ResultUtil.success();
    }
}
