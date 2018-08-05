package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

    ProductInfo findByProductName(String productName);
    List<ProductInfo> findByProductStatus(Integer status);
    List<ProductInfo> findByProductNameLike(String productName);

}
