CREATE DATABASE IF NOT EXISTS `estar` DEFAULT CHARACTER SET utf8mb4;
USE `estar`;

-- 1.用户基础信息表
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '姓名',
    `user_name`   VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户名',
    `phone`       VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '手机号',
    `addr`        VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `current_addr`VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '现居住址',
    `sex`         TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:male(男) 1:female(女)',
    `aadhaar_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'Aadhaar号码',
    `pan_no`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'pan号码',
    `password`    VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '密码',
    `salt`        VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '盐值',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `channel`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户渠道来源',
    `source`            VARCHAR(200) NOT NULL DEFAULT '' COMMENT '用户来源说明',
    `status`      BIGINT(2) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户状态 1正常 2禁用 3账户锁定',
    `black_flag`  BIGINT(2) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '是否黑名单 0否 1是',
    `delete_flag` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',

    UNIQUE INDEX `udx_u_name` (`user_name`),
    UNIQUE INDEX `udx_u_phone` (`phone`),
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
    `aadhaar_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',
    `sex`         TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:male(男) 1:female(女)',
    `dob`         VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'Aadhaar卡姓名',
    `addr`        VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `user_id`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
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
    `pan_no`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',
    `dob`         VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'pan卡姓名',
    `father_name` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'pan父亲姓名',
    `user_id`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
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
    `voterid_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',
    `name`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '选民证姓名',
    `father_name` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '选民证父亲姓名',
    `addr`        VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '选民证地址',
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
    `passport_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户passport号',
    `type`         VARCHAR(10)         NOT NULL DEFAULT '' COMMENT 'passport类型',
    `country_code` VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '国际编码',
    `given_name`   VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'given_name',
    `nationality`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '国籍',
    `sex`          TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:male(男) 1:female(女)',
    `dob`          VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `place_birth`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '出生地',
    `place_issue`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '签发地址',
    `issue_date`   VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '签发日期',
    `expiry_date`  VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '到期日期',
    `father_name`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '父亲姓名',
    `mother_name`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '母亲姓名',
    `spouse_name`  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '配偶姓名',
    `addr`         VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '地址',
    `file_no`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '文件编码',
    `user_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
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
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `dl_no`          VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '驾照编码',
    `cov`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `doi`            VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `dob`            VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `bg`             VARCHAR(4)          NOT NULL DEFAULT '' COMMENT '',
    `name`           VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '姓名',
    `sdw`            VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `addr`           VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '地址',
    `pin`            VARCHAR(20)         NOT NULL DEFAULT '' COMMENT 'pin号',
    `issu_authority` VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '签发授权机构',
    `user_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
    `last_editor`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`     DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_dl_no` (`dl_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户驾照信息表';


-- 6.催收任务信息表
DROP TABLE IF EXISTS `collection_task`;
CREATE TABLE IF NOT EXISTS `collection_task`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '预期订单id',
    `collection_status` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收任务状态 0未催收 1催收中 2催收完成 3催收失败',
    `proof_img`         VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '催收凭证url/img',
    `repay_method`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款方式 0线上 1线下',
    `repay_money`       DECIMAL(10,2)      NOT NULL DEFAULT 0 COMMENT '回款还款金额',
    `repay_status`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款状态 0未还款 1.部分还款 2.结清',
    `repay_proof_img`   VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '还款凭证url/img',
    `close_flag`        TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务是否关闭 0未关闭 1已关闭',
    `close_reason`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '任务关闭原因',
    `user_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务分配人',
    `last_editor`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '催收任务信息表';