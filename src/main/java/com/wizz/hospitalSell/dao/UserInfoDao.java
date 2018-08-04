package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUserName(String userName);
    List<UserInfo> findAllByUserId(Integer userId);

}
