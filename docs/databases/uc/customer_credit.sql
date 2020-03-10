CREATE DATABASE IF NOT EXISTS `wolf_credit` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_credit`;


DROP TABLE IF EXISTS `credit_config`;
CREATE TABLE IF NOT EXISTS `credit_config`
(
    `id`                        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',

    `enable`                    TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否启用 0未启用，1已启用',
    `promote_per_order`         DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '最大信用额度',
    `min_amount`                DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '最大信用额度',
    `max_amount`                DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '最小信用额度',
    `currency`                  INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `min_promotion_period`      INT(11) NOT NULL DEFAULT 0 COMMENT '提额间隔(单位:秒)',
    `max_times_per_day`         INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `max_times_per_week`        INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `max_times_per_month`       INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `max_times_per_year`        INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',

    `version`                   INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`               TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`               BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`                DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`                DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_org(`org_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户信用额度';


DROP TABLE IF EXISTS `credit_line`;
CREATE TABLE IF NOT EXISTS `credit_line`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `account_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',

    `amount`                DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '信用额度',
    `currency`              INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `times_latest_day`      INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `times_latest_week`     INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `times_latest_month`    INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `times_latest_year`     INT(11) NOT NULL DEFAULT 0  NOT NULL COMMENT '提升次数',
    `promoted_at`           DATETIME  COMMENT '提额时间',

    `version`               INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`           TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`            DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_account(`account_id`, `org_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户信用额度';

DROP TABLE IF EXISTS `credit_log`;
CREATE TABLE IF NOT EXISTS `credit_log`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `account_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `operation_type`TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '日志类型',

    `source_amount` DECIMAL(15, 4) unsigned NOT NULL DEFAULT 0 COMMENT '历史金额',
    `target_amount` DECIMAL(15, 4) unsigned NOT NULL DEFAULT 0 COMMENT '更新金额',

    `tags`          VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '标签',
    `memo`          VARCHAR(500)         NOT NULL DEFAULT '' COMMENT '备注',

    `source_version`INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `target_version`INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '更新版本号',

    `editor`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `delete_flag`   TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`    DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_org(`org_id`, `account_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '信用额度变更记录';


