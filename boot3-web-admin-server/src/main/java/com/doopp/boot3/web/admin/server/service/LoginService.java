package com.doopp.boot3.web.admin.server.service;

import com.doopp.boot3.web.admin.server.pojo.request.adminlogin.LoginRequest;
import com.doopp.boot3.web.admin.server.pojo.response.adminlogin.LoginResponse;

public interface LoginService {

    LoginResponse ossLogin(LoginRequest request);
}
