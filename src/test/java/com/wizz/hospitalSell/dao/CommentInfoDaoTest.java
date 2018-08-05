package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.mapper.CommentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/5 15:54
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentInfoDaoTest {

    @Autowired
    CommentInfoDao commentInfoDao;

    @Test
    public void countOfTasteScoreByProductId() {
        System.out.println(commentInfoDao.countOfTasteScoreByProductId("1",5));
    }
}