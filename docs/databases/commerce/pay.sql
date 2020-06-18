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
    `paid_at`               DATETIME            COMMENT '支付时间',
    `out_paid_at`           DATETIME            COMMENT '外部支付时间',

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

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE IF NOT EXISTS `transaction`
(
    `id`                    BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT,
    `account_id`            BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
    `payee_id`              BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `payment_no`            VARCHAR(32)             NOT NULL DEFAULT '' COMMENT '',
    `transaction_type`      TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1, 入帐； 2，退款；...',
    `payment_channel`       SMALLINT(6) UNSIGNED NOT NULL DEFAULT 0,

    `currency`              INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `amount`                DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0.00,

    `settlement_no`         VARCHAR(32)             NOT NULL DEFAULT '' COMMENT '',
    `settlement_state`      TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `settled_at`            DATETIME            COMMENT '结算时间',

    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_payee` (`payee_id`, `created_at`, `transaction_type`),
    INDEX `idx_pay` ( `payment_no`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = 'transaction';

DROP TABLE IF EXISTS `settlement`;
CREATE TABLE IF NOT EXISTS `settlement`
(
    `id`                    BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT,
    `account_id`            BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `settlement_no`         VARCHAR(32)             NOT NULL DEFAULT '' COMMENT '',
    `settlement_type`       TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1, 入帐； 2，扣款；...',
    `state`                 TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,

    `currency`              INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `transaction_count`     INT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易数量',
    `transaction_amount`    DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0.00 COMMENT '交易金额',

    `deduct_count`          INT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '退款数量',
    `deduct_amount`         DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0.00 COMMENT '退款金额',

    `fee_count`             INT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '手续费数量',
    `fee_amount`            DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0.00 COMMENT '手续费金额',

    `tax_count`             INT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '税费数量',
    `tax_amount`            DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0.00 COMMENT '税费金额',

    `settled_at`            DATETIME            COMMENT '结算时间',

    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_account(`account_id`, `settled_at`, `state`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = 'settlement';


DROP TABLE IF EXISTS `balance`;
CREATE TABLE IF NOT EXISTS `balance`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `account_id`            BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '帐户ID',
    `account_name`          VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `currency`              INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `available_amount`      DECIMAL(15, 4) NOT NULL DEFAULT 0.00,
    `blocked_amount`        DECIMAL(15, 4) NOT NULL DEFAULT 0.00,
    `unavailable_amount`    DECIMAL(15, 4) NOT NULL DEFAULT 0.00,

    `locker_flag`           TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '',
    `locker`                VARCHAR(50)         DEFAULT NULL COMMENT '',
    `locked_at`             DATETIME            COMMENT '锁定时间',

    `version`               INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`            DATETIME            ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_account(`account_id`, `currency`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '余额';


DROP TABLE IF EXISTS `balance_log`;
CREATE TABLE IF NOT EXISTS `balance_log`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `account_id`            BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '帐户ID',
    `currency`              INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `source_amount`         DECIMAL(15, 4) NOT NULL DEFAULT 0.00,
    `target_amount`         DECIMAL(15, 4) NOT NULL DEFAULT 0.00,
    `memo`                  VARCHAR(2000),

    `delete_flag`           TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_account(`account_id`, `created_at`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '余额日志';



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


