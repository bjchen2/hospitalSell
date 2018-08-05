package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.form.UserInfoUpdateForm;
import com.wizz.hospitalSell.service.UserService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户根据userid查询用户信息
     */

    @GetMapping("/info")
    public ModelAndView info(@RequestParam(required = false) Integer userId){
        ModelAndView mav=new ModelAndView();
        UserInfo userInfo=userService.findByUserId(userId);
        Map<String,String> m=new HashMap<>();
        m.put("userId",userInfo.getUserId().toString());//后者将id类型转换成string类型。
        m.put("userIcon",userInfo.getUserIcon());
        m.put("userAddress",userInfo.getUserAddress());
        m.put("userPhone",userInfo.getUserPhone());
        mav.addObject("map",m);

        return mav;
    }

    /**
     * 更新用户信息
     * @return
     */
    @PostMapping("/update")
    public ModelAndView update(@Valid UserInfoUpdateForm userInfoUpdateForm, BindingResult bindingResult){
        Map<String,String> m=new HashMap<>();
        UserInfo uf=new UserInfo();
        if(bindingResult.hasErrors()){
            m.put("error",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("/update",m);
        }

        BeanUtils.copyProperties(userInfoUpdateForm,uf);

        if(userService.isExistByUserName(uf.getUserName())){
            m.put("message","用户名已被占用，请换一个用户名");
            return new ModelAndView("/update",m);

        }

        userService.save(uf);
        m.put("message","更新成功");
        return new ModelAndView("/success",m);


    }



}
