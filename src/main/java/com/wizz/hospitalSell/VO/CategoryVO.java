package com.wizz.hospitalSell.VO;

import lombok.Data;

import java.util.List;

/**
 * 类目概要
 * 有时domain层实体类的某些信息比较 敏感/私密/无用 ，则不返回给前端，所以定义一个VO对象用于响应
 * Created By Cx On 2018/8/1 21:33
 */
@Data
public class CategoryVO {

    private String categoryName;

    private Integer categoryType;

    private List<ProductInfoVO> products;

    public CategoryVO(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public CategoryVO() {
    }
}
