package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.dao.ProductCategoryDao;
import com.wizz.hospitalSell.domain.ProductCategory;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created By Cx On 2018/8/1 18:54
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryDao categoryRepository;

    @Override
    public ProductCategory getOne(Integer id) {
        Optional<ProductCategory> category = categoryRepository.findById(id);
        return category.isPresent() ? category.get() : null;
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return categoryRepository.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory findByCategoryType(Integer type) {
        return categoryRepository.findByCategoryType(type);
    }

    @Override
    public ProductCategory save(ProductCategory p) {
        if (p.getCategoryType().equals(0)){
            log.error("【修改类目】默认类目不可修改,categoryType={}",p.getCategoryType());
            throw new SellException(ResultEnum.DEFAULT_CATEGORY_ERROR);
        }
        return categoryRepository.save(p);
    }
}
