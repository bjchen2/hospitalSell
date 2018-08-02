package com.wizz.hospitalSell.service;


import com.wizz.hospitalSell.domain.AdminInfo;

/**
 * 用户有关service，卖家端（PC端后台管理）
 * Created By Cx On 2018/7/27 19:23
 */
public interface AdminService {

    /**
     * 根据管理员的用户名和密码查询管理员是否存在
     */
    Boolean isAdminExist(AdminInfo adminInfo);

    /**
     * 查询管理员账号是否存在
     */
    Boolean isAdminNameExist(String adminName);

    /**
     * 创建管理员
     */
    AdminInfo create(AdminInfo AdminInfo);

}
