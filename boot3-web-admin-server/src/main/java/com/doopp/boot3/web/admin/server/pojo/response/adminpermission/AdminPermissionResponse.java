package com.doopp.boot3.web.admin.server.pojo.response.adminpermission;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminPermissionResponse implements Serializable {

    private String account;

    private String hashPassword;

    private String ossAuthorization;
}
