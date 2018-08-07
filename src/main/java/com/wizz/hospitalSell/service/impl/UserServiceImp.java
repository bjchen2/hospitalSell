package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.dao.UserInfoDao;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Cx On 2018/8/1 22:44
 */
@Service
@Slf4j
public class UserServiceImp implements UserService {
    @Autowired
    UserInfoDao userInfoDao;
    @Override
    public UserInfo save(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo findByOpenid(String openId) {
        return null;
    }
    @Override
    public UserInfo findByUserId(Integer userId) {

        return userInfoDao.findByUserId(userId);

    }
    @Override
    public Boolean isExistByUserName(String userName){

        return userInfoDao.existsByUserName(userName);
    }
}
