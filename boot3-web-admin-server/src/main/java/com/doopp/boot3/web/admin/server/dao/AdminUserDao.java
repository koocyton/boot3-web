package com.doopp.boot3.web.admin.server.dao;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doopp.boot3.web.admin.server.dao.mapper.AdminUserMapper;
import com.doopp.boot3.web.admin.server.pojo.entity.AdminUser;

@Repository
public class AdminUserDao extends ServiceImpl<AdminUserMapper, AdminUser> {
    
    public IPage<AdminUser> getPageList() {
        IPage<AdminUser> pageQuery = Page.of(1, 30);
        return lambdaQuery()
            .page(pageQuery);
    }

    public AdminUser getOrCreateByUsername(String username) {
        AdminUser adminUser = getByUsername(username);
        if (ObjectUtils.isEmpty(adminUser)) {
            adminUser = new AdminUser();
            adminUser.setUsername(username);
            adminUser.setAccount(username);
            save(adminUser);
            return adminUser;
        }
        return adminUser;
    }

    public AdminUser getByUsername(String username) {
        return lambdaQuery()
            .eq(AdminUser::getUsername, username)
            .one();
    }
}
