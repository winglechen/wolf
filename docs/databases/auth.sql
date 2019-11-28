-- .后台用户管理信息表
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '密码',
    `password`    VARCHAR(64)         NOT NULL DEFAULT '' COMMENT '密码',
    `salt`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '盐值',
    `mobile`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '手机号',
    `real_name`   VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '姓名',
    `nickname`    VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户名',
    `address`     VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `gender`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:male(男) 1:female(女)',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `status`      BIGINT(2) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户状态 1正常 2禁用 3账户锁定',
    `delete_flag` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_account` (`account`),
    UNIQUE INDEX `udx_mobile` (`mobile`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '后台用户管理信息表';



-- 1.角色信息表
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_name`   VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '角色名称',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '角色信息表';


-- 2.用户角色关联信息表
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `uid`         BIGINT(20)          NOT NULL DEFAULT '' COMMENT '用户id',
    `role_id`     BIGINT(20)          NOT NULL DEFAULT '' COMMENT '角色id',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户角色关联信息表';

-- 3.权限信息表
DROP TABLE IF EXISTS `permission`;
CREATE TABLE IF NOT EXISTS `permission`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '资源名称',
    `code`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '权限代码字符串',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '权限信息表';

-- 4.角色权限信息表
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '角色id',
    `permission_id` BIGINT(20) NOT NULL DEFAULT 0 COMMENT '权限id',

    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '角色权限信息表';

