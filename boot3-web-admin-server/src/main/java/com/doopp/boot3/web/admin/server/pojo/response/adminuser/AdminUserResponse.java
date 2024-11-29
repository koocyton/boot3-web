package com.doopp.boot3.web.admin.server.pojo.response.adminuser;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserResponse implements Serializable {

    private String account;

    private String hashPassword;

    private String ossAuthorization;
}
