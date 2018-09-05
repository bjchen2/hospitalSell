package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.dao.UserInfoDao;
import com.wizz.hospitalSell.domain.UserInfo;
import com.wizz.hospitalSell.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(cacheNames = "user", key = "#userInfo.userOpenid")
    public UserInfo save(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#openid", unless = "#result == null ")
    public UserInfo findByOpenid(String openid) {
        return userInfoDao.findByUserOpenid(openid);
    }

    @Override
    public UserInfo findByUserId(Integer userId) {
        return userInfoDao.findByUserId(userId);
    }

    @Override
    public Boolean isExistByUserId(Integer userId) {
        return userInfoDao.existsByUserId(userId);
    }
}
