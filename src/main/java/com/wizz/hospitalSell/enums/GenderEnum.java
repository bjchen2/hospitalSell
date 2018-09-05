package com.wizz.hospitalSell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 * Created By Cx On 2018/8/11 15:39
 */
@Getter
@AllArgsConstructor
public enum GenderEnum implements CodeEnum {

    UNKNOWN(0, "未知"),
    MAN(1, "男性"),
    WOMAN(2, "女性");

    //状态码
    private Integer code;
    //状态描述
    private String message;
}
