package com.wizz.hospitalSell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By Cx On 2018/8/7 16:21
 */
@Getter
@AllArgsConstructor
public enum ProductSortEnum {
    SCORE(1, "通过好评排序"),
    PRICE(2, "通过价格排序");

    //状态码
    private Integer code;
    //状态描述
    private String message;
}
