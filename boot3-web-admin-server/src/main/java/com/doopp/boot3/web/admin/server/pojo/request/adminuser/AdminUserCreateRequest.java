package com.doopp.boot3.web.admin.server.pojo.request.adminuser;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserCreateRequest implements Serializable {

    private String account;

    private String hashPassword;

    private String ossAuthorization;
}
