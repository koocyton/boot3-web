package com.doopp.boot3.web.admin.server.pojo.request.adminrole;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRoleUpdateRequest implements Serializable {

    private Long id;

    private String name;

    private String type;

    private String pid;
}
