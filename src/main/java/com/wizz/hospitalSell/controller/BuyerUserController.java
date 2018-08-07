package com.wizz.hospitalSell.controller;


import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.service.UserService;
import com.wizz.hospitalSell.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/info")
public class BuyerUserController {

    @Autowired
    UserService userService;

    /**
     * 用户根据userid查询用户信息
     */

    @GetMapping("/{userId}")
    public ResultVO info(@PathVariable Integer userId){
        Boolean s=userService.isExistByUserId(userId);
        if(s==false){
            return ResultUtil.error("没有此用户，请去注册");
        }
        UserInfo userInfo=userService.findByUserId(userId);

        userInfo.setUserName(userInfo.getUserName());
        userInfo.setUserIcon(userInfo.getUserIcon());
        userInfo.setUserAddress(userInfo.getUserAddress());
        userInfo.setUserPhone(userInfo.getUserPhone());

        return ResultUtil.success(userInfo);

    }

    /**
     * 更新用户信息
     * @return
     */
    @PostMapping("/{userId}")
    public ResultVO update(@PathVariable Integer userId ,@RequestParam String userName,
                           @RequestParam String userAddress,@RequestParam String userPhone){

        Boolean s=userService.isExistByUserId(userId);
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