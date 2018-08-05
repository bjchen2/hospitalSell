package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.enums.ProductStatusEnum;
import com.wizz.hospitalSell.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created By Cx On 2018/8/5 10:15
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoService productInfoService;

    @Test
    public void findOne() {
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void findAll() {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime") ;
        Page<ProductInfo> productInfoPage = productInfoService.findAll(PageRequest.of(0,1,sort));
        System.out.println(productInfoPage.getContent());
    }

    @Test
    @Rollback(value = false)
    public void save() {
        ProductInfo product = new ProductInfo();
        product.setCategoryType(2);
        product.setProductDescription("肉肉肉！！！！");
        product.setProductIcon("http://www.xxxx.jpg");
        product.setProductId("5");
        product.setProductName("肉");
        product.setProductPrice(new BigDecimal(1.3));
        product.setProductStatus(ProductStatusEnum.UP.getCode());
        product.setSellerPhone("123456789");
        productInfoService.save(product);
    }

    @Test
    public void increaseSales() {
    }

    @Test
    public void onSale() {
    }

    @Test
    public void offSale() {
    }
}