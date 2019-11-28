CREATE DATABASE IF NOT EXISTS `estar` DEFAULT CHARACTER SET utf8mb4;
USE `estar`;

-- ------------------------------用户认证日志信息表  未改完----------------------------------------


-- 1.用户认证日志信息表
DROP TABLE IF EXISTS `auth_log`;
CREATE TABLE IF NOT EXISTS `auth_log`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `channel_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户渠道id',
    `real_name`   VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户姓名',
    `nickname`    VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '昵称',
    `mobile`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '手机号',
    `address`     VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `gender`      VARCHAR(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `aadhaar_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'Aadhaar号码',
    `pan_no`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'pan号码',
    `delete_flag` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `auth_status` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '认证状态 0未认证 1:Aadhaar认证 2:PAN认证 3:护照认证 4:驾驶证认证 5:选民证认证',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',

    UNIQUE INDEX `udx_aadhaar_no` (`aadhaar_no`),
    UNIQUE INDEX `udx_pan_no` (`pan_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户认证日志信息表';

-- ------------------------------用户认证日志信息表  未写完----------------------------------------
