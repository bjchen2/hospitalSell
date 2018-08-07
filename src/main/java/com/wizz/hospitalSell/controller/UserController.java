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
@RequestMapping("/user/info")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户根据userid查询用户信息
     */

    @GetMapping("/userId")
    public ResultVO info(@RequestParam(required = false) String userName,@RequestParam(required = false) String userIcon,
                         @RequestParam(required = false) String userAddress,@RequestParam(required = false) String userPhone){
        Boolean s=userService.isExistByUserName(userName);
        if(s=false){
            return ResultUtil.error("没有此用户，请去注册");
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserIcon(userIcon);
        userInfo.setUserAddress(userAddress);
        userInfo.setUserPhone(userPhone);

        return ResultUtil.success(userInfo);

    }

    /**
     * 更新用户信息
     * @return
     */
    @PostMapping("/userId")
    public ResultVO update(@RequestParam(required =false) String userName,
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

