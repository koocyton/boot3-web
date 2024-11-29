package com.doopp.boot3.web.admin.server.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.doopp.boot3.web.admin.server.pojo.dto.auth.AuthInfo;
import com.doopp.boot3.web.admin.server.pojo.request.adminlogin.LoginRequest;
import com.doopp.boot3.web.admin.server.pojo.response.adminlogin.LoginResponse;
import com.doopp.boot3.web.admin.server.service.LoginService;
import com.doopp.boot3.web.core.annotation.NoLogin;
import com.doopp.boot3.web.core.response.Result;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private LoginService loginService;

    @NoLogin
    @PostMapping("/ssoLogin")
    public Result<LoginResponse> ssoLogin(@RequestBody LoginRequest request) {
        return Result.tryOk(()->loginService.ossLogin(request));
    }

    @GetMapping("/authInfo")
    public Result<AuthInfo> authInfo(@SessionAttribute("AuthInfo") AuthInfo authInfo) {
        return Result.ok(authInfo);
    }
}
