package com.wizz.hospitalSell.dao;

import com.wizz.hospitalSell.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer status);

    List<ProductInfo> findByProductNameLike(String productName);

}
