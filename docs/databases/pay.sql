CREATE DATABASE IF NOT EXISTS `wolf_pay` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_pay`;

-- -----------------------------------------------------
-- Table `pay`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE IF NOT EXISTS `pay`
(
    `id`                    BIGINT(20) UNSIGNED    NOT NULL AUTO_INCREMENT,
    `pay_no`                VARCHAR(30)         NOT NULL DEFAULT '' COMMENT '',

    `payer_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payer_name`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',
    `payee_id`              BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_name`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '',

    `amount`                DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0,
    `payment_type`          TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
    `payment_method`        TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
    `payment_state`         TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
    `notify_state`          TINYINT(2) UNSIGNED NOT NULL DEFAULT 0,

    `out_trade_no`          VARCHAR(32)         NOT NULL DEFAULT '' COMMENT '',
    `desc`                  VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '',

    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`            DATETIME            ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_pay_no` (`pay_no` ASC)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '';


-- -----------------------------------------------------
-- Table `pay_notify_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pay_notify_log`;
CREATE TABLE IF NOT EXISTS `pay_notify_log`
(
    `id`             INT(10) UNSIGNED    NOT NULL AUTO_INCREMENT,
    `payment_method` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0,
    `response`       TEXT                NOT NULL,
    `created_at`     DATETIME            NOT NULL DEFAULT '2019-12-01 00:00:00',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '';


-- -----------------------------------------------------
-- Table `pay_request_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pay_request_log`;
CREATE TABLE IF NOT EXISTS `pay_request_log`
(
    `id`         INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `pay_no`     VARCHAR(30)      NOT NULL,
    `response`   TEXT             NOT NULL,
    `created_at` DATETIME         NOT NULL DEFAULT '2019-12-01 00:00:00',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '';


-- -----------------------------------------------------
-- Table `pay_trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pay_trade`;
CREATE TABLE IF NOT EXISTS `pay_trade`
(
    `id`                 INT(10) UNSIGNED    NOT NULL AUTO_INCREMENT,
    `pay_no`             VARCHAR(30)         NOT NULL,
    `trade_no`           VARCHAR(30)         NOT NULL,
    `payment_type`       TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `trade_amount`       DECIMAL(15, 4) UNSIGNED    NOT NULL DEFAULT 0,
    `payer_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payment_state`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `notify_state`       TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
    `out_trade_no`      VARCHAR(32)         NOT NULL DEFAULT '',
    `created_at`         DATETIME            NOT NULL DEFAULT '2019-12-01 00:00:00',
    `updated_at`         DATETIME            NOT NULL DEFAULT '2019-12-01 00:00:00',
    INDEX `trade_no` (trade_no),
    KEY (pay_no),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '';


-- -----------------------------------------------------
-- Table `trade_notify_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trade_notify_log`;
CREATE TABLE IF NOT EXISTS `trade_notify_log`
(
    `id`             INT(10) UNSIGNED    NOT NULL AUTO_INCREMENT,
    `pay_no`         VARCHAR(30)         NOT NULL,
    `payer_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payee_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0,
    `payment_type`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 0,
    `payment_method` TINYINT(4)          NOT NULL DEFAULT 0,
    `payment_state`  TINYINT(4)          NOT NULL DEFAULT 0,
    `trade_no`       VARCHAR(30)         NOT NULL,
    `out_trade_no`   VARCHAR(32)         NOT NULL,
    `created_at`     DATETIME            NOT NULL DEFAULT '2019-12-01 00:00:00',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '';


-- -----------------------------------------------------
-- Table `trade_request_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `trade_request_log`;
CREATE TABLE IF NOT EXISTS `trade_request_log`
(
    `id`         INT(10) UNSIGNED NOT NULL,
    `pay_no`     VARCHAR(30)      NOT NULL,
    `request`    TEXT             NOT NULL,
    `created_at` DATETIME         NOT NULL DEFAULT '2019-12-01 00:00:00',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '';