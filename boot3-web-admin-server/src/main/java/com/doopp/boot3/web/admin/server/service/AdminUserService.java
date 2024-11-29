package com.doopp.boot3.web.admin.server.service;

import com.doopp.boot3.web.admin.server.pojo.entity.AdminUser;

public interface AdminUserService {

    AdminUser getOrCreateByUsername(String username);
}
