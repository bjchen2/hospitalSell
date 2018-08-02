package com.wizz.hospitalSell.service;

import com.wizz.hospitalSell.domain.UserInfo;

/**
 * 用户有关service   微信端
 * Created By Cx On 2018/8/1 22:43
 */
public interface UserService {

    /**
     * 新增某个用户
     */
    UserInfo save(UserInfo userInfo);

    /**
     * 查询某个用户
     */
    UserInfo findByOpenid(String openId);
}
