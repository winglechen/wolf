CREATE DATABASE IF NOT EXISTS `wolf_pay` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_pay`;

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `payment_no`            VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '',

    `payer_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payer_name`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `payee_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_name`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',

    `amount`                DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0,
    `currency`              INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `state`                 TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `payment_type`          TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `payment_method`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,

    `trade_no`              VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '',
    `out_trade_no`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',

    `goods_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `goods_name`            VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `tags`                  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `attachment`            VARCHAR(1000)        NOT NULL DEFAULT '' COMMENT '',

    `version`               INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`            DATETIME            ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX `idx_trade_no` (`trade_no`, `state`) COMMENT '覆盖索引：检查重复支付问题',
    UNIQUE INDEX `udx_pay_no` (`payment_no` ASC),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '支付';

DROP TABLE IF EXISTS `payment_state_log`;
CREATE TABLE IF NOT EXISTS `payment_state_log`
(
    `id`                INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `payment_no`        VARCHAR(32)          NOT NULL DEFAULT '' COMMENT '支付号',
    `payment_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',

    `payer_id`          BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
    `payee_id`          BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `source_state`      TINYINT(4) unsigned NOT NULL DEFAULT 0 COMMENT '历史状态',
    `target_state`      TINYINT(4) unsigned NOT NULL DEFAULT 0 COMMENT '更新状态',

    `tags`              VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '支付标签',

    `source_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `target_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新版本号',
    `editor`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_payment_no(`payment_no`),
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '支付状态变更记录';


DROP TABLE IF EXISTS `trade_merge`;
CREATE TABLE IF NOT EXISTS `trade_merge`
(
    `id`                    BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT,
    `payment_no`            VARCHAR(32)             NOT NULL DEFAULT '' COMMENT '',
    `trade_no`              VARCHAR(32)             NOT NULL DEFAULT '' COMMENT '',
    `payer_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,

    `amount`                DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0.00,

    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_pay` ( `payment_no`, `trade_no`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '合并支付';


DROP TABLE IF EXISTS `payment_log`;
CREATE TABLE IF NOT EXISTS `payment_log`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `payment_no`        VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '',

    `payer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,

    `trade_no`          VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '',
    `out_trade_no`      VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '',

    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `log_type`          TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `payment_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,

    `data`              TEXT,
    `tags`              VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',

    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '支付日志';


DROP TABLE IF EXISTS `razorpay_account`;
CREATE TABLE IF NOT EXISTS `razorpay_account`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,

    `payer_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payer_name`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `payee_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_name`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',

    `state`                 TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,

    `contact_id`            VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `contact`               VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `email`                 VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `contact_type`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `contact_active`        TINYINT(2) UNSIGNED NOT NULL DEFAULT 0,

    `account_id`            VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `account_type`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `account_name`          VARCHAR(120)        NOT NULL DEFAULT '' COMMENT '',
    `bank_name`             VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '',
    `account_ifsc`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `account_number`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `account_active`        TINYINT(2) UNSIGNED NOT NULL DEFAULT 0,

    `vpa_address`           VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',

    `card_name`             VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `card_last4`            VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `card_network`          VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `card_type`             VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',
    `card_issuer`           VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',

    `tags`                  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',

    `delete_flag`           TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX `udx_payer`(`payer_id`, `payee_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = 'razorpay_account';


