package com.wizz.hospitalSell.service;

import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * Created By Cx On 2018/6/10 21:13
 */
public interface ProductInfoService {

    /**
     * 根据id查询商品信息
     */
    ProductInfo findOne(String id);

    /**
     * 查询所有上架商品   --客户端使用
     */
    List<ProductInfo> findUpAll();

    /**
     * 根据分页条件，按时间排序，返回所有商品信息    --服务端使用
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 添加商品
     */
    ProductInfo save(ProductInfo product);

    /**
     * 增加销量
     */
    void increaseSales(List<CartDto> cartDtoList);

    /**
     * 减少销量
     */
    void decreaseSales(List<CartDto> cartDtoList);

    /**
     * 上架
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     */
    ProductInfo offSale(String productId);

    /**
     * 通过关键字查询商品
     */
    List<ProductInfo> findByKey(String key);
}
