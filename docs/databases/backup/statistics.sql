CREATE DATABASE IF NOT EXISTS `wolf_datav` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_datav`;

-- 1.运营日报
DROP TABLE IF EXISTS `daily_koi`;
CREATE TABLE IF NOT EXISTS `daily_koi`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                  DATE                NOT NULL COMMENT '统计日期',

    `pv`                    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'PV',
    `uv`                    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'UV',
    `register_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '注册数',
    `basic_info_count`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '基础信息数',
    `liveness_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '活体认证数',
    `bankcard_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '银行卡绑定数',
    `aadhaar_count`         INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'aadhaar数',
    `pan_card_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'PAN Card数',
    `passport_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '护照数',
    `driving_license_count` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '驾照数',
    `voter_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '选民卡数',
    `kyc_count`             INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'KYC数',
    `credit_promote_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '信用额提升数',
    `auth_success_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '完成认证数',

    `delete_flag`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '运营日报';

-- 2.放款日报
DROP TABLE IF EXISTS `daily_loan`;
CREATE TABLE IF NOT EXISTS `daily_loan`
(
    `id`                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`               BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                 DATE                NOT NULL COMMENT '统计日期',

    `request_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '借款申请数',
    `request_approved`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '申请通过数',
    `request_refused`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '申请拒绝数',
    `loan_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '放款数',
    `loan_amount`          DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '放款金额',
    `first_loan_count`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次放款数',
    `first_loan_amount`    DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次放款金额',
    `due_count`            INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期数',
    `due_amount`           DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期金额',
    `overdue_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期数',
    `overdue_amount`       DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期金额',
    `first_overdue_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首逾数',
    `first_overdue_amount` DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首逾金额',
    `repay_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款数',
    `repay_amount`         DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款金额',
    `loss_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损数',
    `loss_amount`          DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损金额',

    `delete_flag`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`           DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '放款日报';


-- -- 3.还款日报
-- DROP TABLE IF EXISTS `daily_repay`;
--  CREATE TABLE IF NOT EXISTS `daily_repay`
--  (
--      `id`                        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
--      `org_id`                    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
--      `date`                      DATE NOT NULL COMMENT '统计日期',
--
--      `delete_flag`               TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
--      `created_at`                DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--      UNIQUE INDEX udx_date(`org_id`, `date`),
--      PRIMARY KEY (`id`)
--  ) ENGINE = InnoDB
--    DEFAULT CHARACTER SET = utf8mb4
--      COMMENT = '还款日报';

-- 4.分期日报
DROP TABLE IF EXISTS `daily_installment`;
CREATE TABLE IF NOT EXISTS `daily_installment`
(
    `id`                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`               BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                 DATE                NOT NULL COMMENT '统计日期',
    `installment_no`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '分期数',

    `due_count`            INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期数',
    `due_amount`           DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期金额',
    `overdue_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期数',
    `overdue_amount`       DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期金额',
    `first_overdue_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首逾数',
    `first_overdue_amount` DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首逾金额',
    `repay_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款数',
    `repay_amount`         DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款金额',
    `loss_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损数',
    `loss_amount`          DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损金额',

    `delete_flag`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`           DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`, `installment_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '分期日报';

-- 5.催收日报
DROP TABLE IF EXISTS `daily_collection`;
CREATE TABLE IF NOT EXISTS `daily_collection`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                DATE                NOT NULL COMMENT '统计日期',

    `collection_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收数',
    `new_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '新增案件数',
    `new_amount`          DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '新增案件金额',
    `success_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '成功催回数',
    `success_amount`      DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '成功催回金额',
    `easy_success_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '当日催当日还数',
    `easy_success_amount` DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '当日催当日还金额',
    `fail_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '失败数',
    `fail_amount`         DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '失败金额',

    `delete_flag`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`          DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '催收日报';




-- 6.催收员日报
DROP TABLE IF EXISTS `daily_collector`;
CREATE TABLE IF NOT EXISTS `daily_collector`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `account_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '帐户ID',
    `date`                DATE                NOT NULL COMMENT '统计日期',

    `collection_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收数',
    `new_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '新增案件数',
    `new_amount`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '新增案件金额',
    `success_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '成功催回数',
    `success_amount`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '成功催回金额',
    `easy_success_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '当日催当日还数',
    `easy_success_amount` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '当日催当日还金额',
    `fail_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '失败数',
    `fail_amount`         INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '失败金额',

    `delete_flag`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`          DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '催收员日报';

-- 7.催收员统计
DROP TABLE IF EXISTS `statistics_collector`;
CREATE TABLE IF NOT EXISTS `statistics_collector`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `account_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '帐户ID',

    `collection_count` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收数',
    `case_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '新增案件数',
    `case_amount`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '新增案件金额',
    `success_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '成功催回数',
    `success_amount`   INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '成功催回金额',
    `fail_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '失败数',
    `fail_amount`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '失败金额',

    `delete_flag`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`       DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_account (`org_id`, `account_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '催收员统计';


DROP TABLE IF EXISTS `track_collection`;
CREATE TABLE `track_collection`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '组织ID',
    `date`                DATE                NOT NULL COMMENT '统计日期',

    `new_count`           INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '新增案件数',
    `new_amount`          DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '新增案件金额',
    `easy_success_count`  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '当日催当日还数',
    `easy_success_amount` DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '当日催当日还金额',

    `d2`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第2天逾期订单数',
    `d3`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第3天逾期订单数',
    `d4`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第4天逾期订单数',
    `d5`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第5天逾期订单数',
    `d6`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第6天逾期订单数',
    `d7`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第7天逾期订单数',
    `w2`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第2周逾期订单数',
    `w3`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第3周逾期订单数',
    `w4`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '第4周逾期订单数',
    `m1`                  INT(11) UNSIGNED    NOT NULL DEFAULT '0' COMMENT '30天逾期订单数',

    `bad_debt`            DECIMAL(15, 4)      NOT NULL DEFAULT '0.00' COMMENT '坏账率',
    `delete_flag`         TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `created_at`          DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_date` (`org_id`, `date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT ='新催收日报';

DROP TABLE IF EXISTS `offset_holder`;
CREATE TABLE IF NOT EXISTS `offset_holder`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,

    `table_name`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '表名',
    `shard_key`         VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'shardKey',
    `task_name`         VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '任务名',
    `offset`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'offset',

    `version`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'offsetHolder';




