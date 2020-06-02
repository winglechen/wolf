USE `wolf_datav`;

-- 运营概况日报
DROP TABLE IF EXISTS `beat`;
CREATE TABLE IF NOT EXISTS `beat`
(
    `id`                    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `org_id`                BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `account_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `device_id`             VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '设备唯一标识',
    `device_type`           VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '设备类型',

    `ip`                    VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'ip',
    `latitude`              DECIMAL(10, 4)        NOT NULL DEFAULT '0.0000' COMMENT '纬度',
    `longitude`             DECIMAL(10, 4)        NOT NULL DEFAULT '0.0000' COMMENT '经度',

    `event_type`            VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '事件类型',
    `event`                 VARCHAR(2000)         NOT NULL DEFAULT '' COMMENT '事件',
    `event_context`         VARCHAR(2000)         NOT NULL DEFAULT '' COMMENT '事件上下文',
    `event_memo`            VARCHAR(2000)         NOT NULL DEFAULT '' COMMENT '事件备注',

    `created_at`            DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '事件日志';