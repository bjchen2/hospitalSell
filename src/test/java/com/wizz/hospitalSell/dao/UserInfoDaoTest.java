package com.wizz.hospitalSell.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/19 23:53
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserInfoDaoTest {

    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void findByUserId() {
    }

    @Test
    public void findByUserOpenid() {
        System.out.println(userInfoDao.findByUserOpenid("oE3wM5I60PBz2cEfeWqumbS1gHkU"));
    }

    @Test
    public void existsByUserName() {
    }

    @Test
    public void existsByUserId() {
    }

    @Test
    public void save() {
    }
}