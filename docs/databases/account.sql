CREATE DATABASE IF NOT EXISTS `wolf_account` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_account`;

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账号id',
    `account`      VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '手机号码',

    `password`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '密码',
    `salt`         VARCHAR(12)         NOT NULL DEFAULT '',

    `account_type` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号类型 0 默认、1 手机、2 email、3 alipay open_id 4 wechat open_id 5 wechat union_id',
    `roles`        VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '账号角色 多角色用,分开 ',
    `source`       VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '帐号来源 ',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_account` (`account` ASC)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 10000000000001 COMMENT 'account';

DROP TABLE IF EXISTS `organization`;
CREATE TABLE IF NOT EXISTS `organization`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_name`    VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '组织名称',
    `org_type`    TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认0 未定义， 1 店铺， 2点位',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 100000001 COMMENT = '组织表';


DROP TABLE IF EXISTS `account_alipay`;
CREATE TABLE IF NOT EXISTS `account_alipay`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `open_id`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '支付宝 open id',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户支付宝授权关系表';


DROP TABLE IF EXISTS `account_wechat`;
CREATE TABLE IF NOT EXISTS `account_wechat`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `open_id`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '微信 open id',
    `union_id`    VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '微信 union id',
    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号\\n',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户微信授权关系表';


DROP TABLE IF EXISTS `access_token`;
CREATE TABLE IF NOT EXISTS `access_token`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `client_id`     VARCHAR(10)         NOT NULL DEFAULT '' COMMENT 'client id',
    `access_token`  VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'access token值',
    `refresh_token` VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'refresh token值',
    `expired_at`    DATETIME            NOT NULL DEFAULT '2019-09-01 00:00:00' COMMENT '过期时间',
    `version`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',

    `delete_flag`   TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`    DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_acc_tk` (`access_token`)
) ENGINE = InnoDB COMMENT = 'access_token表';


DROP TABLE IF EXISTS `refresh_token`;
CREATE TABLE IF NOT EXISTS `refresh_token`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `client_id`     VARCHAR(10)         NOT NULL DEFAULT '' COMMENT 'client id',
    `refresh_token` VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'refresh token值',
    `expired_at`    DATETIME            NOT NULL DEFAULT '2019-09-01 00:00:00' COMMENT '过期时间',
    `version`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`   TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`    DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = 'refresh_token表';
