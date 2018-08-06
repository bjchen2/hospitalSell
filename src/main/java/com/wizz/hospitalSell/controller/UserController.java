package com.wizz.hospitalSell.controller;

import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.service.UserService;
import com.wizz.hospitalSell.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户根据userid查询用户信息
     */

    @GetMapping("/info")
    public ResultVO info(@RequestParam(required = false) Integer userId){
        UserInfo userInfo=userService.findByUserId(userId);
        if(userInfo==null){
            return ResultUtil.error("没有此用户，请去注册");
        }
        return ResultUtil.success(userInfo);

    }

    /**
     * 更新用户信息
     * @return
     */
    @PostMapping("/update")
    public ResultVO update(@RequestParam(required =false )Integer userId,@RequestParam(required =false) String userName,
                             @RequestParam(required =false) String userAddress,@RequestParam(required =false) String userPhone){

        Boolean s=userService.isExistByUserName(userName);
        if(s==true){
            return ResultUtil.error("此用户名已经存在，请重新输入用户名");
        }
        UserInfo us=new UserInfo();
        us.setUserName(userName);
        us.setUserAddress(userAddress);
        us.setUserPhone(userPhone);
        userService.save(us);

        return ResultUtil.success(us);
    }



}
