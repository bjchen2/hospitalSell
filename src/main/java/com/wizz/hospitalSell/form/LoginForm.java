package com.wizz.hospitalSell.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 * Created By Cx On 2018/8/1 16:09
 */
@Data
public class LoginForm {

    @NotBlank(message = "用户名不能为空")
    private String adminName;

    @NotBlank(message = "密码不能为空")
    private String adminPass;

    private String remember;
}
