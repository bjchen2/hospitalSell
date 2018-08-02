package com.wizz.hospitalSell.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 新增管理员表单
 * Created By Cx On 2018/8/2 12:54
 */
public class RegisterForm {

    @NotBlank(message = "已有管理员用户名不能为空")
    private String username;

    @NotBlank(message = "已有管理员密码不能为空")
    private String password;

    @NotBlank(message = "新增管理员用户名不能为空")
    private String newUsername;

    @NotBlank(message = "新增管理员密码不能为空")
    private String newPassword;
}
