package com.wizz.hospitalSell.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Cx On 2018/8/5 15:48
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentInfoRepositoryTest {

    @Autowired
    CommentInfoRepository commentInfoRepository;

    @Test
    public void getByCommentId() {
    }

    @Test
    public void findAllByProductIdOrderByCreateTime() {
        System.out.println(commentInfoRepository.findAllByProductIdOrderByCreateTimeDesc("1"));
    }


}