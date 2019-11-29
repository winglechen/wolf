CREATE DATABASE IF NOT EXISTS `onion` DEFAULT CHARACTER SET utf8mb4;
USE `onion`;

-- 1.催收任务信息表
DROP TABLE IF EXISTS `overdue_case`;
CREATE TABLE IF NOT EXISTS `overdue_case`
(
    `id`              BIGINT(20) UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `org_id`          BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '组织id',
    `account_id`      BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '账号id',
    `assigner_id`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '任务分配人',

    `contract_no`     VARCHAR(30)          NOT NULL DEFAULT '' COMMENT '合同号',
    `trade_no`        VARCHAR(30)          NOT NULL DEFAULT '' COMMENT '交易号',
    `installment_no`  SMALLINT(6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期号',
    `case_account_id` BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '案件人id',
    `case_org_id`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '案件人的组织id',
    `status`          TINYINT(2) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '案件处理状态 0待处理 1处理中 2案件完成',
    `memo`            VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '催收情况描述',

    `delete_flag`     TINYINT(2) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `version`         INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '版本号',
    `last_editor`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`      DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '催收任务信息表';


-- 2.催收日志信息表
DROP TABLE IF EXISTS `collection`;
CREATE TABLE IF NOT EXISTS `collection`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织id',
    `account_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收员id',
    `case_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收任务id',
    `collection_method` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收方式 1短信 2电信 3.微信 4.其他',
    `contacts`          VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '催款联系人',
    `contacts_status`   TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '联系状态 1联系不上 2承诺还款 3.延期 4.拒绝还款',
    `promise_pay_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '承诺还款时间',
    `repay_status`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款状态 0未还款 1.部分还款 2.结清',
    `repay_method`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款方式 0线上 1线下',
    `repay_money`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款金额',
    `describe`          VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '催收情况描述',
    `collection_proof`  VARCHAR(1000)       NOT NULL DEFAULT '' COMMENT '催收凭证url/img',
    `repay_proof`       VARCHAR(1000)       NOT NULL DEFAULT '' COMMENT '还款凭证url/img',

    `delete_flag`       TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `version`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `last_editor`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '催收任日志信息表';


-- 3.组织催收关联关系表
DROP TABLE IF EXISTS `org_collection`;
CREATE TABLE IF NOT EXISTS `org_collection`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织id',
    `collection_org_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收组织id',
    `delete_flag`       TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `version`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `last_editor`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '组织催收关联关系表';
