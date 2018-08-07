package com.wizz.hospitalSell;



import com.wizz.hospitalSell.controller.UserController;
import com.wizz.hospitalSell.dao.UserInfoDao;
import com.wizz.hospitalSell.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void test(){
        String userIcon="212121";
        String userName="wyk";
        String userAddress="232323";
        String userPhone="121331311";


        System.out.println(userController.info(userName,userIcon,userAddress,userPhone));

    }
}
