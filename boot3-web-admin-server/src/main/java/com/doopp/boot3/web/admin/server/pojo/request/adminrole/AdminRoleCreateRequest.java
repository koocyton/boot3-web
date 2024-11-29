package com.doopp.boot3.web.admin.server.pojo.request.adminrole;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRoleCreateRequest implements Serializable {

    private String name;

    private String type;

    private String pid;
}
