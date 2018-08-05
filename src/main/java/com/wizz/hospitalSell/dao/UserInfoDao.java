package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUserName(String userName);
    UserInfo findByUserId(Integer userId);
    UserInfo findByUserOpenid(String userOpenid);
}
