package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.ProductCategory;
import com.wizz.hospitalSell.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/5 11:00
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void getOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByCategoryTypeIn() {
    }

    @Test
    public void findByCategoryType() {
    }

    @Test
    @Rollback(value = false)
    public void save() {
        ProductCategory productCategory = new ProductCategory("女生最爱",3);
        System.out.println(categoryService.save(productCategory));
        ProductCategory productCategory1 = new ProductCategory("男生最爱",2);
        System.out.println(categoryService.save(productCategory1));
        ProductCategory productCategory2 = new ProductCategory("最热",1);
        System.out.println(categoryService.save(productCategory2));
    }
}