package com.wizz.hospitalSell.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 新增管理员表单
 * Created By Cx On 2018/8/2 12:54
 */
@Data
public class RegisterForm {

    @NotBlank(message = "已有管理员账号不能为空")
    private String adminName;

    @NotBlank(message = "已有管理员密码不能为空")
    private String adminPass;

    @NotBlank(message = "新增管理员账号不能为空")
    private String newAdminName;

    @NotBlank(message = "新增管理员密码不能为空")
    private String newAdminPass;
}
