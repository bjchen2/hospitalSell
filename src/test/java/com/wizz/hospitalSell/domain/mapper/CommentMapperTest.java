package com.wizz.hospitalSell.domain.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/8 9:25
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentMapperTest {

    @Autowired
    CommentMapper commentMapper;

    @Test
    public void test1() {
    }
}