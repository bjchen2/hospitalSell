package com.wizz.hospitalSell.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/4 18:20
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminInfoDaoTest {

    @Autowired
    AdminInfoDao adminInfoDao;

    @Test
    public void existsByAdminNameAndAdminPass() {
    }

    @Test
    public void existsByAdminName() {
        System.out.println(adminInfoDao.existsByAdminName("admin"));
    }
}