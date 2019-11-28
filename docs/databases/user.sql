CREATE DATABASE IF NOT EXISTS `estar` DEFAULT CHARACTER SET utf8mb4;
USE `estar`;

-- 1.用户基础信息表
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
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
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户基础信息表';


-- 2.用户Aadhaar卡信息表
DROP TABLE IF EXISTS `aadhaar_info`;
CREATE TABLE IF NOT EXISTS `aadhaar_info`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `aadhaar_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',

    `uid`         VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '',
    `gender`      VARCHAR(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `dob`         VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'Aadhaar卡姓名',
    `address`     VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `vid`         VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '',
    `yob`         VARCHAR(10)         NOT NULL DEFAULT '' COMMENT '',
    `co`          VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `loc`         VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '',
    `vtc`         VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `po`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `dist`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `subdist`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `state`       VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `pc`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_aadhaar_no` (`aadhaar_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户Aadhaar卡信息表';


-- 2.用户pan_card卡信息表
DROP TABLE IF EXISTS `pan_card`;
CREATE TABLE IF NOT EXISTS `pan_card`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `pan_no`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',
    `dob`         VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'pan卡姓名',
    `father_name` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'pan父亲姓名',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_pan_no` (`pan_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户pan卡信息表';


-- 3.用户选民证信息表
DROP TABLE IF EXISTS `voterid_card`;
CREATE TABLE IF NOT EXISTS `voterid_card`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `voterid_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '选民证姓名',
    `father_name` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '选民证父亲姓名',
    `address`     VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '选民证地址',
    `sign_date`   VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '签发日期',
    `user_id`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_voterid_no` (`voterid_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户选民证信息表';


-- 4.用户护照信息表
DROP TABLE IF EXISTS `passport_info`;
CREATE TABLE IF NOT EXISTS `passport_info`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `passport_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户passport号',
    `type`         VARCHAR(10)         NOT NULL DEFAULT '' COMMENT 'passport类型',
    `country_code` VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '国际编码',
    `given_name`   VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'given_name',
    `nationality`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '国籍',
    `gender`       VARCHAR(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `dob`          VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `birth_place`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '出生地',
    `issue_place`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '签发地址',
    `issue_date`   VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '签发日期',
    `expiry_date`  VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '到期日期',
    `father_name`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '父亲姓名',
    `mother_name`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '母亲姓名',
    `spouse_name`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '配偶姓名',
    `address`      VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '地址',
    `file_no`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '文件编码',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_passport_no` (`passport_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户护照信息表';


-- 5.用户驾照信息表
DROP TABLE IF EXISTS `driving_license`;
CREATE TABLE IF NOT EXISTS `driving_license`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `dl_no`             VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '驾照编码',
    `cov`               VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `doi`               VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `dob`               VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `bg`                VARCHAR(4)          NOT NULL DEFAULT '' COMMENT '',
    `name`              VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '姓名',
    `sdw`               VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `address`           VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '地址',
    `pin`               VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'pin号',
    `issuing_authority` VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '签发授权机构',
    `last_editor`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_dl_no` (`dl_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户驾照信息表';


