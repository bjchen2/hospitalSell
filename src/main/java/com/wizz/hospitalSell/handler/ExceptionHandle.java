package com.wizz.hospitalSell.handler;

import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.config.ProjectConfig;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.exception.SellerAuthorizeException;
import com.wizz.hospitalSell.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常捕获类
 * Created By Cx On 2018/8/1 15:09
 */
@ControllerAdvice
public class ExceptionHandle {

    @Autowired
    private ProjectConfig projectConfig;

    //拦截登录异常,若检验redis和cookie没有正确的登录值，则跳转回登录界面
    @ExceptionHandler(SellerAuthorizeException.class)
    public ModelAndView handleAuthorizeException() {
        return new ModelAndView("redirect:".concat("/user/index"));
    }

    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException e){
        return ResultUtil.error(e.getCode(),e.getMessage());
    }
}
