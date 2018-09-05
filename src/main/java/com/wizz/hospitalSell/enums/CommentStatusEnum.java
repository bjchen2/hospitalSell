package com.wizz.hospitalSell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评论状态枚举
 * Created By Cx On 2018/8/25 22:46
 */
@Getter
@AllArgsConstructor
public enum CommentStatusEnum implements CodeEnum {
    NO_COMMENT(0, "未评论"),
    COMMENTED(1, "已评论");

    //状态码
    private Integer code;
    //状态描述
    private String message;
}
