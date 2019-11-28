-- 1.逾期订单案件表
DROP TABLE IF EXISTS `overdue_case`;
CREATE TABLE IF NOT EXISTS `overdue_case`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_id`    BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '逾期订单id',
    `status`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '案件处理状态 0未处理 2处理中 3案件完成',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '逾期订单案件表';

-- 2.催收员信息表
# DROP TABLE IF EXISTS `collector`;
# CREATE TABLE IF NOT EXISTS `collector`
# (
#     `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
#     `name`             BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '催收员姓名',
#     `mobile`           BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '催收员手机号',
#     `status`           TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 0正常 1禁用',
#     `delete_flag`      TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
#     `account_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
#     `org_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
#     `collector_org_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收员组织ID',
#     `version`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
#     `last_editor`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
#     `created_at`       DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `updated_at`       DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   DEFAULT CHARACTER SET = utf8mb4 COMMENT = '催收员信息表';


-- 3.催收任务信息表
DROP TABLE IF EXISTS `collection`;
CREATE TABLE IF NOT EXISTS `collection`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '预期订单id',
    `collector_id`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收员id',
    `account_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逾期订单组织ID',
    `collector_org_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收员组织ID',
    `version`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `status`           TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收结果状态 1催收失败 2催收中 3催收成功',
    `describe`         VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '催收情况描述',
    `uid`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '任务分配人',
    `last_editor`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`       DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '催收任务信息表';


-- 4.催收日志信息表
DROP TABLE IF EXISTS `collection_log`;
CREATE TABLE IF NOT EXISTS `collection_log`
(
    `id`              BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收订单id',
    `collection_id`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收任务id',
    `collector_id`    BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收员id',
    `proof_img`       VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '催收凭证url/img',
    `describe`        VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '催收情况描述',
    `repay_method`    TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款方式 0线上 1线下',
    `repay_money`     DECIMAL(10, 2)      NOT NULL DEFAULT 0 COMMENT '回款还款金额',
    `repay_status`    TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回款还款状态 0未还款 1.部分还款 2.结清',
    `repay_proof_img` VARCHAR(200)        NOT NULL DEFAULT '' COMMENT '还款凭证url/img',
    `last_editor`     BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`      DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '催收任日志信息表';


-- 5.组织催收关联关系表
DROP TABLE IF EXISTS `organization_collection`;
CREATE TABLE IF NOT EXISTS `organization_collection`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`           BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单组织ID',
    `collector_org_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '催收组织ID',
    `type`             TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '关系类型：0催收 1.  2.',
    `version`          INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `last_editor`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`       DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '组织催收关联关系表';


-- 6.后台用户管理信息表
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account`     VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '密码',
    `password`    VARCHAR(64)         NOT NULL DEFAULT '' COMMENT '密码',
    `salt`        VARCHAR(50)         NOT NULL DEFAULT '' COMMENT '盐值',
    `mobile`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '手机号',
    `real_name`   VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '姓名',
    `nickname`    VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户名',
    `address`     VARCHAR(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `gender`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:male(男) 1:female(女)',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `status`      BIGINT(2) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '用户状态 1正常 2禁用 3账户锁定',
    `delete_flag` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX `udx_account` (`account`),
    UNIQUE INDEX `udx_mobile` (`mobile`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '后台用户管理信息表';