# springboot3 dubbo-client apollo mybatis-plus redission 

## create

```sql
CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `username` varchar(32) DEFAULT NULL,
  `hash_password` varchar(64) DEFAULT NULL COMMENT '密码',
  `open_id` varchar(64) DEFAULT NULL COMMENT 'open id',
  `open_platform` char(8) DEFAULT NULL,
  `role_ids` varchar(128) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE,
  KEY `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB COMMENT='管理员';
```
