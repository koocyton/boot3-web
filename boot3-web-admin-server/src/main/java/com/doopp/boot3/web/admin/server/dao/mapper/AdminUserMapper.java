package com.doopp.boot3.web.admin.server.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doopp.boot3.web.admin.server.pojo.entity.AdminUser;

@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
}
