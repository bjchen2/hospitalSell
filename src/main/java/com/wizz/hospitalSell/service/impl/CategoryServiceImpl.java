package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.domain.ProductCategory;
import com.wizz.hospitalSell.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created By Cx On 2018/8/1 18:54
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public ProductCategory getOne(Integer id) {
        return null;
    }

    @Override
    public List<ProductCategory> findAll() {
        return null;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return null;
    }

    @Override
    public ProductCategory findByCategoryType(Integer type) {
        return null;
    }

    @Override
    public ProductCategory save(ProductCategory p) {
        return null;
    }
}
