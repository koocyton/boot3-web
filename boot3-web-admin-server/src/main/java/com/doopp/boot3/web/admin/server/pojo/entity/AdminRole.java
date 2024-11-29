package com.doopp.boot3.web.admin.server.pojo.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class AdminRole {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String rolename;

    private Long pid;

    private Long rootId;

    private String namePath;

    private String idPath;

    private Integer level;

    private Integer sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
