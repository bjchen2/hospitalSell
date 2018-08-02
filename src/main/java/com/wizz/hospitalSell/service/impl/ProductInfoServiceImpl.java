package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.CartDto;
import com.wizz.hospitalSell.service.ProductInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Cx On 2018/8/1 18:56
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{

    @Override
    public ProductInfo findOne(String id) {
        return null;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return null;
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ProductInfo save(ProductInfo product) {
        return null;
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    public ProductInfo onSale(String productId) {
        return null;
    }

    @Override
    public ProductInfo offSale(String productId) {
        return null;
    }
}
