CREATE DATABASE IF NOT EXISTS `wolf_org` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_org`;

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company`
(
    `org_id`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgID',
    `org_type`              TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认0 未定义， 1 店铺， 2点位',

    `company_name`          VARCHAR(200)         NOT NULL DEFAULT '' COMMENT 'company_name',
    `company_logo`          VARCHAR(200)         NOT NULL DEFAULT '' COMMENT 'company_logo',
    `tags`                  VARCHAR(500)            NOT NULL DEFAULT '' COMMENT 'tags',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`org_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'company';