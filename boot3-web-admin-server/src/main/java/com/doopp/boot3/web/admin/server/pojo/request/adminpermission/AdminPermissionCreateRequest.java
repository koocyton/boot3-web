package com.doopp.boot3.web.admin.server.pojo.request.adminpermission;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminPermissionCreateRequest implements Serializable {

    private String name;

    private String type;

    private String pid;
}
