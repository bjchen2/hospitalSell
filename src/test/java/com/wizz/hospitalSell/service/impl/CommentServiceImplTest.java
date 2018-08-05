package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.CommentInfo;
import com.wizz.hospitalSell.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/5 18:59
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @Test
    public void findDtosByProductInfos() {
    }

    @Test
    public void findInfosByProductId() {
    }

    @Test
    public void findAllDtos() {
    }

    @Test
    public void findDtosByProductName() {
    }

    @Test
    @Rollback(value = false)
    public void create() {
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setCommentId("3");
        commentInfo.setPackingScore(4);
        commentInfo.setTasteScore(2);
        commentInfo.setProductId("2");
        commentInfo.setQualityScore(1);
        commentInfo.setUserOpenid("1");
        System.out.println(commentService.create(commentInfo));
    }
}