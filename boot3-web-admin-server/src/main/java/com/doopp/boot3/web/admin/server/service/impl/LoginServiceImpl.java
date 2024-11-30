package com.doopp.boot3.web.admin.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.doopp.boot3.web.admin.server.component.AuthJwtComponent;
import com.doopp.boot3.web.admin.server.component.LdapComponent;
import com.doopp.boot3.web.admin.server.pojo.entity.AdminUser;
import com.doopp.boot3.web.admin.server.pojo.request.adminlogin.LoginRequest;
import com.doopp.boot3.web.admin.server.pojo.response.adminlogin.LoginResponse;
import com.doopp.boot3.web.admin.server.service.AdminUserService;
import com.doopp.boot3.web.admin.server.service.LoginService;
import com.doopp.boot3.web.core.exception.AssertException;
import com.doopp.boot3.web.core.exception.AssertMessageEnum;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    protected AdminUserService adminUserService;

    @Resource
    protected AuthJwtComponent authJwtComponent;

    @Resource
    protected LdapComponent ldapComponent;

    @Override
    public LoginResponse ossLogin(LoginRequest request) {
        String username = ldapComponent.verifyTicketAndGetUsername(request.getOpenToken());
        // log.info("username:{}", username);
        // AdminUser adminUser = adminUserService.getOrCreateByUsername(username);
        // if (ObjectUtils.isEmpty(adminUser)) {
        //    throw new AssertException(AssertMessageEnum.USER_NOT_EXIST);
        // }
        AdminUser adminUser = new AdminUser();
        adminUser.setId(1L);
        adminUser.setUsername(username);
        String sessionToken = authJwtComponent.buildTokenForUser(adminUser);
        LoginResponse response = new LoginResponse();
        response.setToken(sessionToken);
        response.setUsername(adminUser.getUsername());
        return response;
    }
}
