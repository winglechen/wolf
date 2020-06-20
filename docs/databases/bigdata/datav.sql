CREATE DATABASE IF NOT EXISTS `wolf_datav` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_datav`;

-- 运营概况日报
DROP TABLE IF EXISTS `daily_koi`;
CREATE TABLE IF NOT EXISTS `daily_koi`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                  DATE                NOT NULL COMMENT '统计日期',
    `source`                VARCHAR(50) NOT NULL DEFAULT '' COMMENT '渠道',

    `pv`                    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'PV',
    `uv`                    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'UV',
    `install_count`         INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '安装数',
    `register_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '注册数',
    `basic_info_count`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '基础信息数',
    `liveness_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '活体认证数',
    `bank_card_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '银行卡绑定数',
    `aadhaar_count`         INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'aadhaar数',
    `pan_card_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'PAN Card数',
    `passport_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '护照数',
    `driving_license_count` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '驾照数',
    `voter_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '选民卡数',
    `kyc_count`             INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT 'KYC数',
    `auth_success_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '完成认证数',
    `credit_promote_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '信用额提升数',

    `delete_flag`           TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`, `source`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '运营日报';


-- 通用交易日报
DROP TABLE IF EXISTS `daily_trade`;
CREATE TABLE IF NOT EXISTS `daily_trade`
(
    `id`                        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                      DATE                NOT NULL COMMENT '统计日期',

    `trade_type`                INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '订单类型',
    `state`                     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '订单状态：0代表新增',
    `source`                    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '渠道',

    `trade_count`               INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '交易数',
    `buyer_count`               INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '交易人数',
    `trade_amount`              DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '交易金额',

    `delete_flag`               TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`                DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_date (`org_id`, `date`, `trade_type`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '交易日报';

DROP TABLE IF EXISTS `daily_sms`;
CREATE TABLE IF NOT EXISTS `daily_sms`
(
    `id`                        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                      DATE                NOT NULL COMMENT '统计日期',
    `source`                    VARCHAR(50) NOT NULL DEFAULT '' COMMENT '渠道',

    `sms_type`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '订单类型',
    `sms_count`                 INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '交易数',

    `delete_flag`               TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`                DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_org (`org_id`, `date`, `sms_type`),
    INDEX idx_date(`date`, `sms_type`, `sms_count`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'sms日报';


-- 放款概况日报
DROP TABLE IF EXISTS `daily_loan`;
CREATE TABLE IF NOT EXISTS `daily_loan`
(
    `id`                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`               BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                 DATE                NOT NULL COMMENT '统计日期',

    `request_count`             INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '借款申请数',
    `first_request_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次申请数',
    `request_approved`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '申请通过数',
    `first_request_approved`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次申请通过数',
    `request_refused`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '申请拒绝数',
    `first_request_refused`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次申请拒绝数',
    `approved_rate`             DECIMAL(10, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '通过比率',
    `first_approved_rate`       DECIMAL(10, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次申请通过比率',
    `loan_count`                INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '放款数',
    `first_loan_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次放款数',
    `loan_amount`               DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '放款金额',
    `first_loan_amount`         DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次放款金额',
    `loan_rate`                 DECIMAL(10, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '放款比率',
    `first_loan_rate`           DECIMAL(10, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首次申请放款比率',

    `delete_flag`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`           DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '放款日报';

-- 还款概况日报
DROP TABLE IF EXISTS `daily_repay`;
CREATE TABLE IF NOT EXISTS `daily_repay`
(
    `id`                   BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`               BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                 DATE                NOT NULL COMMENT '统计日期',
    `installment_no`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '分期数：0代表合计',

    `due_count`            INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期数',
    `first_due_count`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期数',
    `due_amount`           DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期金额',
    `first_due_amount`     DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '到期金额',

    `overdue_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期数',
    `overdue_amount`       DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期金额',
    `first_overdue_count`  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首逾数',
    `first_overdue_amount` DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '首逾金额',

    `repay_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款数',
    `first_repay_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款数',
    `repay_amount`         DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款金额',
    `first_repay_amount`   DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '还款金额',

    `loss_count`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损数',
    `first_loss_count`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损数',
    `loss_amount`          DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损金额',
    `first_loss_amount`    DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损金额',

    `delete_flag`          TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`           DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_date (`org_id`, `date`, `installment_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '分期日报';

-- 催收概况日报
DROP TABLE IF EXISTS `daily_collection`;
CREATE TABLE IF NOT EXISTS `daily_collection`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `date`                DATE                NOT NULL COMMENT '统计日期',

    `collection_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收数',
    `collection_amount`   DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收金额',
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
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '催收日报';

-- 催收员概况日报
DROP TABLE IF EXISTS `daily_collector`;
CREATE TABLE IF NOT EXISTS `daily_collector`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `account_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '帐户ID',
    `date`                DATE                NOT NULL COMMENT '统计日期',

    `collection_count`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收数',
    `collection_amount`   DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '催收数',
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
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '催收员日报';

-- 催收员模型
DROP TABLE IF EXISTS `model_collector`;
CREATE TABLE IF NOT EXISTS `model_collector`
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
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '催收员统计';

-- 还款跟踪
DROP TABLE IF EXISTS `track_repay`;
CREATE TABLE `track_repay`
(
    `id`                  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `loan_date`           DATE                NOT NULL COMMENT '放款日期',
    `due_date`            DATE                NOT NULL COMMENT '到期日期',
    `goods_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
    `installment_no`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '分期数：0代表合计',

    `prepay_count`        INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期数',
    `prepay_amount`       DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期金额',
    `repay_count`         INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期数',
    `repay_amount`        DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期金额',
    `overdue_count`       INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期数',
    `overdue_amount`      DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '逾期金额',
    `loss_count`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '资损数',
    `loss_amount`         DECIMAL(15, 4)      NOT NULL DEFAULT 0.00 COMMENT '资损金额',
    `partly_loss_count`   INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '部分资损数',
    `partly_loss_amount`  DECIMAL(15, 4)      NOT NULL DEFAULT 0.00 COMMENT '部分资损金额',
    `m1_loss_rate`        DECIMAL(8, 4)      NOT NULL DEFAULT 0.00 COMMENT '30天坏账率',

    `d1_amount`           DECIMAL(15, 4)      NOT NULL DEFAULT 0.00 COMMENT 'd1金额',
    `d1`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '当日催当日还数',
    `d2`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第2天还款订单数',
    `d3`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第3天还款订单数',
    `d4`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第4天还款订单数',
    `d5`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第5天还款订单数',
    `d6`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第6天还款订单数',
    `d7`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第7天还款订单数',
    `w2`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第2周还款订单数',
    `w3`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第3周还款订单数',
    `w4`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '第4周还款订单数',
    `m1`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '30天还款订单数',
    `mn`                  INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '30天后还款订单数',

    `delete_flag`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`          DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_date` (`org_id`, `due_date`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4
COMMENT ='新催收日报';

-- offsetHolder
DROP TABLE IF EXISTS `offset_holder`;
CREATE TABLE IF NOT EXISTS `offset_holder`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,

    `table_name`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '表名',
    `sharding_key`      VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'shardingKey',
    `source`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'source',
    `sink`              VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'sink',

    `offset`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0  COMMENT 'offset',

    `locker`            TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'locker',
    `lock_at`           DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',

    `version`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX  udx_task(`table_name`, `sharding_key`, `source`, `sink`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'offsetHolder';




