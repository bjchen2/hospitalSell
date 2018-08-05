package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.dao.ProductInfoDao;
import com.wizz.hospitalSell.domain.ProductInfo;
import com.wizz.hospitalSell.dto.CartDto;
import com.wizz.hospitalSell.enums.ProductStatusEnum;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created By Cx On 2018/8/1 18:56
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProductInfoServiceImpl implements ProductInfoService{

    @Autowired
    ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String id) {
        Optional<ProductInfo> productInfo = productInfoDao.findById(id);
        //isPresent方法,判断返回的Optional是否为有价值的值（即是否不为空），若不为空则为true，否则false
        if (productInfo.isPresent()){
            return productInfo.get();
        }
        return null;
    }

    @Override
    //买家端仅展示上架商品
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    //卖家端显示所有商品
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo product) {
        return productInfoDao.save(product);
    }

    @Override
    public void increaseSales(List<CartDto> cartDtoList) {
        //修改后的product列表
        List<ProductInfo> changeProducts = new ArrayList<>();
        for (CartDto cartDto : cartDtoList){
            ProductInfo product = findOne(cartDto.getProductId());
            if (product == null){
                //如果商品不存在
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //增库存
            int sales = product.getProductSales() + cartDto.getProductQuantity();
            product.setProductSales(sales);
            changeProducts.add(product);
        }
        productInfoDao.saveAll(changeProducts);
    }

    @Override
    public void decreaseSales(List<CartDto> cartDtoList) {
        List<ProductInfo> changeProducts = new ArrayList<>();
        for (CartDto cartDto : cartDtoList){
            ProductInfo product = findOne(cartDto.getProductId());
            if (product == null){
                //如果商品不存在
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //减库存
            int sales = product.getProductSales() - cartDto.getProductQuantity();
            if (sales < 0){
                //总销量小于零
                sales = 0;
            }
            product.setProductSales(sales);
            changeProducts.add(product);
        }
        productInfoDao.saveAll(changeProducts);
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (ProductStatusEnum.UP.getCode().equals(productInfo.getProductStatus())){
            //商品已上架
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoDao.save(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null){
            log.error("[商品下架]商品不存在，productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (ProductStatusEnum.DOWN.getCode().equals(productInfo.getProductStatus())){
            //商品已下架
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfoDao.save(productInfo);
        return productInfo;
    }
}
