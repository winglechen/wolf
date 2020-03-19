CREATE DATABASE IF NOT EXISTS `wolf_org` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_org`;

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task`
(
    `id`            BIGINT(20) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'ID',
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
    INDEX idx_org(`org_id`, `task_type`, `state`),
    INDEX idx_parent(`parent_id`),
    INDEX idx_staff(`staff_id`, `org_id`, `task_type`, `state`),
    INDEX idx_project(`org_id`, `project_id`, `state`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_detail`;
CREATE TABLE IF NOT EXISTS `task_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `memo`          TEXT,
    `extend_fields` TEXT,

    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务详情';

DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `parent_id`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `name`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '项目名',
    `tags`          VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'tags',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX idx_org(`org_id`, `parent_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'project';

DROP TABLE IF EXISTS `project_detail`;
CREATE TABLE IF NOT EXISTS `project_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `detail`        TEXT,

    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = 'project';

DROP TABLE IF EXISTS `task_scheduler`;
CREATE TABLE IF NOT EXISTS `task_scheduler`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `staff_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',


    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_task(`task_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务调度器';

DROP TABLE IF EXISTS `task_contact`;
CREATE TABLE IF NOT EXISTS `task_contact`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `staff_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `partner_org_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '合作组织ID',
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
    UNIQUE INDEX udx_task(`task_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务联系信息';

DROP TABLE IF EXISTS `task_trade`;
CREATE TABLE IF NOT EXISTS `task_trade`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `partner_org_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '合作组织ID',
    `staff_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `task_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',

    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易单号',
    `installment_no`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期数',
    `trade_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易类型',

    `amount`            DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '本金',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `paid_amount`       DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已还金额',
    `paying_amount`     DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '待审核金额',

    `interest`          DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '利息',
    `interest_rate`     DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '利息比率',
    `interest_unit`     INT(11) NOT NULL DEFAULT 0 COMMENT '利息单位',
    `due_at`            DATE COMMENT '到期时间',
    `period_strategy`   INT(11) NOT NULL DEFAULT 0 COMMENT '时长策略',

    `delete_flag`       TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE INDEX udx_task(`task_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_state_log`;
CREATE TABLE IF NOT EXISTS `task_state_log`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `staff_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `task_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `project_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',

    `source_state`      SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '历史状态',
    `target_state`      SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '更新状态',

    `source_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `target_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新版本号',

    `memo`              VARCHAR(1000) NOT NULL DEFAULT '' COMMENT 'memo',
    `tags`              VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'tags',

    `delete_flag`       TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `editor`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_task(`task_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_assignment_log`;
CREATE TABLE IF NOT EXISTS `task_assignment_log`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `project_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `assigner`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',

    `source_owner`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',
    `target_owner`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ID',

    `source_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `target_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新版本号',

    `memo`              VARCHAR(1000) NOT NULL DEFAULT '' COMMENT 'memo',
    `tags`              VARCHAR(200) NOT NULL DEFAULT '' COMMENT 'tags',

    `delete_flag`       TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `editor`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_task(`task_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';

DROP TABLE IF EXISTS `task_progress`;
CREATE TABLE IF NOT EXISTS `task_progress`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `task_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',

    INDEX idx_task(`task_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '任务';