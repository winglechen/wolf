CREATE DATABASE IF NOT EXISTS `wolf_org` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_org`;

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task`
(
    `id`            BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '组织ID',
    `staff_id`      BIGINT(20) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '员工ID',
    `project_id`    BIGINT(20) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '项目ID',
    `parent_id`     BIGINT(20) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '父ID',

    `name`          VARCHAR(500)            NOT NULL DEFAULT '' COMMENT '任务名',
    `task_type`     TINYINT(4) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '任务类型',
    `state`         TINYINT(4) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '任务状态',
    `priority`      SMALLINT(6) UNSIGNED    NOT NULL DEFAULT 10000 COMMENT '优先级',

    `progress_rate` DECIMAL(8, 4)           NOT NULL DEFAULT 0.00 COMMENT '进度',
    `work_load`     INT(11) UNSIGNED        NOT NULL DEFAULT 0 COMMENT '工作量',
    `story_points`  SMALLINT(6) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '故事点数',
    `start_at`      DATETIME,
    `end_at`        DATETIME,

    `tags`          VARCHAR(500) NOT NULL DEFAULT '' COMMENT 'tags',

    `version`     INT(11) UNSIGNED          NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED       NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED       NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME                  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_detail`;
CREATE TABLE IF NOT EXISTS `task_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务ID',

    `memo`          TEXT,
    `extend_fields` TEXT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务详情';

DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `name`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '项目名',
    `tags`          VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'tags',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'project';

DROP TABLE IF EXISTS `project_detail`;
CREATE TABLE IF NOT EXISTS `project_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `project_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `detail`        TEXT,

    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'project';

DROP TABLE IF EXISTS `task_scheduler`;
CREATE TABLE IF NOT EXISTS `task_scheduler`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `staff_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',


    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务调度器';

DROP TABLE IF EXISTS `task_contact`;
CREATE TABLE IF NOT EXISTS `task_contact`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `partner_org_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `customer_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `contact_person`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '联系人',
    `contact_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '联系方式',

    `contact_state`     TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '联系状态',
    `contact_result`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '结果状态',

    `contact_attachment`VARCHAR(500) NOT NULL DEFAULT '' COMMENT '联系附件',
    `result_attachment` VARCHAR(500) NOT NULL DEFAULT '' COMMENT '结果附件',
    `recontact_at`      DATETIME                COMMENT '再次联系时间',

    `delete_flag` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务联系信息';

DROP TABLE IF EXISTS `task_trade`;
CREATE TABLE IF NOT EXISTS `task_trade`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易单号',
    `installment_no`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期数',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `amount`            DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '总金额',
    `trade_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易类型',

    `delete_flag`       TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_state_log`;
CREATE TABLE IF NOT EXISTS `task_state_log`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_assignment_log`;
CREATE TABLE IF NOT EXISTS `task_assignment_log`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_progress`;
CREATE TABLE IF NOT EXISTS `task_progress`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';