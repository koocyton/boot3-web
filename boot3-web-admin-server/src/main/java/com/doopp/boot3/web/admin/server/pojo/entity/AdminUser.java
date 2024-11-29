package com.doopp.boot3.web.admin.server.pojo.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.doopp.boot3.web.core.typehandler.LongListTypeHandler;

import lombok.Data;

@Data
@TableName(autoResultMap = true)
public class AdminUser {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String account;

    private String username;

    private String hashPassword;

    private String openId;

    private String openPlatform;

    @TableField(typeHandler = LongListTypeHandler.class)
    private List<Integer> roleIds;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
