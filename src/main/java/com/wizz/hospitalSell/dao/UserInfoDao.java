package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUserId(Integer userId);
    UserInfo findByUserOpenid(String userOpenid);
    Boolean existsByUserName(String userName);
    Boolean existsByUserId(Integer userId);

    @Override
    <S extends UserInfo> S save(S s);
}
