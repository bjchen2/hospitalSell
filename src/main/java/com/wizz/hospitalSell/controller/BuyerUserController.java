package com.wizz.hospitalSell.controller;


import com.wizz.hospitalSell.VO.ResultVO;
import com.wizz.hospitalSell.VO.UserVO;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.service.UserService;
import com.wizz.hospitalSell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/user/info")
public class BuyerUserController {

    @Autowired
    UserService userService;

    /**
     * 用户根据userid查询用户信息
     */

    @GetMapping("/{userId}")
    public ResultVO info(@PathVariable Integer userId) {
        if (!userService.isExistByUserId(userId)) {
            //如果用户不存在
            log.error("[用户查询]用户Id不存在，userId={}", userId);
            return ResultUtil.error("用户Id不存在");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userService.findByUserId(userId), userVO);
        return ResultUtil.success(userVO);
    }

    /**
     * 更新用户信息
     */
    @PostMapping("/{userId}")
    public ResultVO update(@PathVariable Integer userId, @RequestBody Map<String, String> data) {
        UserInfo userInfo = userService.findByUserId(userId);
        if (data.get("userName") != null) {
            userInfo.setUserName(data.get("userName"));
        }
        if (data.get("userAddress") != null) {
            userInfo.setUserAddress(data.get("userAddress"));
        }
        if (data.get("userPhone") != null) {
            userInfo.setUserPhone(data.get("userPhone"));
        }
        if (data.get("userGender") != null) {
            userInfo.setUserGender(Integer.valueOf(data.get("userGender")));
        }
        userService.save(userInfo);
        return ResultUtil.success();
    }


}