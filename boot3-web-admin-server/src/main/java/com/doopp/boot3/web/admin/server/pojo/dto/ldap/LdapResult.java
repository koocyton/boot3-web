package com.doopp.boot3.web.admin.server.pojo.dto.ldap;

import lombok.Data;

@Data
public class LdapResult<T> {

    private String code;

    private String message;

    private T data;

}
