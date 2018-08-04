package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory,String> {

    ProductCategory getByCategoryName(String categoryName);
    List<ProductCategory> findByCategoryId(Integer categoryId);

}
