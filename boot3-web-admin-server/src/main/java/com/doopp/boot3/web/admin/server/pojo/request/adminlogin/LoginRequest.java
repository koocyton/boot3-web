package com.doopp.boot3.web.admin.server.pojo.request.adminlogin;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    private String account;

    private String hashPassword;

    private String openToken;

    private String openPlatform;

    private String openId;
}
