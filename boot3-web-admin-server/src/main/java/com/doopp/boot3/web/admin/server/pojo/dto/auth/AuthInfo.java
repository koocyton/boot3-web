package com.doopp.boot3.web.admin.server.pojo.dto.auth;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthInfo {

    private Long userId;

    private String username;

    private List<Integer> roleIds;

    private LocalDateTime tokenExpire;
}
