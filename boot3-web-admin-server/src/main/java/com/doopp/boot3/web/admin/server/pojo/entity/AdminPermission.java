package com.doopp.boot3.web.admin.server.pojo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class AdminPermission {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String requestUri;

    private String symbol;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
