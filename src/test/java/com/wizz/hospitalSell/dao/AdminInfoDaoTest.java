package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

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
//        Jedis jedis = new Jedis("120.78.131.71",6379);
//        System.out.println("连接成功");
//        jedis.lpush("abcd", "123");
        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(5);
        a.add(7);
        System.out.println(JsonUtil.toJson(a));
    }

    @Test
    public void existsByAdminName() {
        System.out.println(adminInfoDao.existsByAdminName("admin"));
    }
}