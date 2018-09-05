package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);

    ProductCategory findByCategoryType(Integer type);

}
