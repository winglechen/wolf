DROP TABLE IF EXISTS `access_log`;
CREATE TABLE IF NOT EXISTS `access_log`
(
    `id`           INT(11) UNSIGNED    NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织id',
    `account_id`   BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '用户id',
    `channel_id`   BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '渠道',
    `channel_flag` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '渠道标识',
    `uuid`         VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户唯一标识',
    `device_id`    VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '设备唯一标识',
    `os_type`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '设备类型；0：安卓；1：iOS',
    `ip`           VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '访问IP',
    `referrer`     VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '访问来源',
    `ua`           VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '访问环境',
    `page`         VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '访问页面',
    `is_deduction` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否扣量 0否 1是',
    `scene`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '场景如 "product:1"',
    `geo`          VARCHAR(255)        NOT NULL DEFAULT '' COMMENT 'gps位置',
    `source`       TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录类型；1:H5 2:App',
    `delete_flag`  TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户流量访问记录';