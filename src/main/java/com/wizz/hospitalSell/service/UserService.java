package com.wizz.hospitalSell.service;


import com.wizz.hospitalSell.domain.UserInfo;

/**
 * 用户有关service
 * Created By Cx On 2018/7/27 19:23
 */
public interface UserService {

    /**
     * 根据用户的openid查询用户信息
     */
    UserInfo findSellerInfoByOpenId(String openid);
}
