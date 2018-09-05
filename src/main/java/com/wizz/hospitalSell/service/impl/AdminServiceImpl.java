package com.wizz.hospitalSell.service.impl;

import com.wizz.hospitalSell.dao.AdminInfoDao;
import com.wizz.hospitalSell.domain.AdminInfo;
import com.wizz.hospitalSell.enums.ResultEnum;
import com.wizz.hospitalSell.exception.SellException;
import com.wizz.hospitalSell.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Cx On 2018/8/1 18:56
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminInfoDao adminInfoDao;


    @Override
    public Boolean isAdminExist(AdminInfo adminInfo) {
        return adminInfoDao.existsByAdminNameAndAdminPass(adminInfo.getAdminName(), adminInfo.getAdminPass());
    }

    @Override
    public Boolean isAdminNameExist(String adminName) {
        return adminInfoDao.existsByAdminName(adminName);
    }

    @Override
    public AdminInfo create(AdminInfo adminInfo) {
        if (adminInfoDao.existsByAdminName(adminInfo.getAdminName())) {
            //如果用户名存在
            log.error("[管理员注册]管理员用户名已存在，adminNam={}", adminInfo.getAdminName());
            throw new SellException(ResultEnum.ADMIN_NAME_EXIST);
        }
        return adminInfoDao.save(adminInfo);
    }
}
