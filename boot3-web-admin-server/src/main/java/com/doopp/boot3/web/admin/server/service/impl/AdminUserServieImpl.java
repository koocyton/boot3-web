package com.doopp.boot3.web.admin.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doopp.boot3.web.admin.server.dao.AdminUserDao;
import com.doopp.boot3.web.admin.server.pojo.entity.AdminUser;
import com.doopp.boot3.web.admin.server.service.AdminUserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminUserServieImpl implements AdminUserService {

    @Resource
    protected AdminUserDao adminUserDao;

    @Override
    public AdminUser getOrCreateByUsername(String username) {
        return adminUserDao.getOrCreateByUsername(username);
    }
}
