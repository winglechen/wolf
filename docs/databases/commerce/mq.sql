CREATE DATABASE IF NOT EXISTS `wolf_mq` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_mq`;

DROP TABLE IF EXISTS `wmq_message`;
CREATE TABLE IF NOT EXISTS `wmq_message`
(
    `id`           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',

    `topic`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'topic',
    `producer`     VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'producer',
    `shard`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'shard',

    `message_id`   VARCHAR(50)          NOT NULL DEFAULT '' COMMENT 'messageId',
    `tags`         VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'tags',
    `message`      VARCHAR(5000)        NOT NULL DEFAULT '' COMMENT 'message',

    `delete_flag`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX `udx_msg` (`message_id` ASC),
    INDEX `idx_topic` (`topic`, `shard`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 1 COMMENT 'message';

DROP TABLE IF EXISTS `wmq_tcc_message`;
CREATE TABLE IF NOT EXISTS `wmq_tcc_message`
(
    `id`           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',

    `topic`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'topic',
    `producer`     VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'producer',
    `shard`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'shard',
    `state`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'state',

    `message_id`   VARCHAR(50)          NOT NULL DEFAULT '' COMMENT 'messageId',
    `tags`         VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'tags',
    `message`      VARCHAR(5000)        NOT NULL DEFAULT '' COMMENT 'message',

    `delete_flag`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_msg` (`message_id` ASC)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 1 COMMENT 'tcc_message';

DROP TABLE IF EXISTS `wmq_scheduled_message`;
CREATE TABLE IF NOT EXISTS `wmq_scheduled_message`
(
    `id`           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',

    `topic`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'topic',
    `producer`     VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'producer',
    `shard`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'shard',

    `type`         TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'type',
    `interval`     INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '间隔时间',
    `effect_at`    DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',

    `message_id`   VARCHAR(50)          NOT NULL DEFAULT '' COMMENT 'messageId',
    `tags`         VARCHAR(100)         NOT NULL DEFAULT '' COMMENT 'tags',
    `message`      VARCHAR(5000)        NOT NULL DEFAULT '' COMMENT 'message',

    `delete_flag`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME            ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_msg` (`message_id` ASC)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 1 COMMENT 'scheduled_message';

DROP TABLE IF EXISTS `wmq_queue`;
CREATE TABLE IF NOT EXISTS `wmq_queue`
(
    `id`           MEDIUMINT(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',

    `topic`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'topic',
    `consumer`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT 'consumer',
    `shard`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'shard',

    `offset`       INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT 'offset',
    `locker`       TINYINT(1) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'locker',
    `locked_at`    DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',

    `delete_flag`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    UNIQUE INDEX `udx_lock`(`topic`, `consumer`, `locker`, `shard`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 1 COMMENT 'queue';

DROP TABLE IF EXISTS `wmq_task`;
CREATE TABLE IF NOT EXISTS `wmq_task`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',

    `topic`        VARCHAR(50)          NOT NULL DEFAULT '' COMMENT 'topic',
    `shard`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'shard',
    `consumer`     VARCHAR(50)          NOT NULL DEFAULT '' COMMENT 'consumer',
    `message_id`    VARCHAR(50)          NOT NULL DEFAULT '' COMMENT 'message_id',

    `offset`       INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT 'offset',
    `state`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT 'state',
    `retry_times`  INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT 'retry times',

    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME            COMMENT '更新时间',

    INDEX `idx_msgid` (`message_id`, `topic`),
    INDEX `idx_timeout` (`state` ASC , `created_at` DESC ),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  AUTO_INCREMENT = 1 COMMENT 'task';


