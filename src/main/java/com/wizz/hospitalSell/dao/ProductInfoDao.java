package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoDao extends JpaRepository<ProductInfo,Integer> {

    ProductInfo findByProductName(String productName);
    List<ProductInfo> findAllByProductId(String productId);

}
