CREATE DATABASE IF NOT EXISTS `estar` DEFAULT CHARACTER SET utf8mb4;
USE `estar`;


-- 1.用户认证日志信息表
DROP TABLE IF EXISTS `auth_log`;
CREATE TABLE IF NOT EXISTS `auth_log`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',
    `org_id`      BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `channel_id`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户渠道id',

    `real_name`   VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户姓名',
    `mobile`      VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '手机号',
    `step`       TINYINT(2)         NOT NULL DEFAULT 0 COMMENT '认证操作步骤 1:Aadhaar认证 2:PAN认证 3:护照认证 4:驾驶证认证 5:选民证认证',
    `card_no`  VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '认证识别证件号码',
    `front_side`  VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`  VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '证件背面图片',

    `delete_flag` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `auth_status` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '认证状态 0未认证 1:Aadhaar认证 2:PAN认证 3:护照认证 4:驾驶证认证 5:选民证认证',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户认证日志信息表';

