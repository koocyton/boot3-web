package com.doopp.boot3.web.admin.server.pojo.response.adminlogin;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponse implements Serializable {

    private String username;

    private String token;
}
