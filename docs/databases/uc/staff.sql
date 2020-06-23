CREATE DATABASE IF NOT EXISTS `wolf_uc` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_uc`;

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账号id',
    `account_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'accountID',
    `org_id`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgID',
    `department_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'department_id',

    `real_name`             VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'real_name',
    `nick_name`             VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'nick_name',
    `avatar`                VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'avatar',
    `birthday`              DATE,
    `gender`                TINYINT(2) UNSIGNED NOT NULL  DEFAULT 0,

    `phone`                 VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'avatar',
    `tel`                   VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'avatar',
    `email`                 VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'avatar',
    `national_id`           VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '身份证号',

    `roles`                 VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'roles',
    `title`                 VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'avatar',
    `p_no`                  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'P级',
    `m_no`                  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'M级',

    `tags`                  VARCHAR(500)            NOT NULL DEFAULT '' COMMENT 'tags',
    `intro`                 VARCHAR(500)            NOT NULL DEFAULT '' COMMENT 'intro',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'staff';