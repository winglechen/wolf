CREATE DATABASE IF NOT EXISTS `wolf_setting` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_setting`;

DROP TABLE IF EXISTS `account_status`;
CREATE TABLE IF NOT EXISTS `account_status`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号id',

    `s1`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s2`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s3`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s4`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s5`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s6`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s7`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s8`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s9`          BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s10`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',

    `s11`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s12`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s13`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s14`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s15`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s16`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s17`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s18`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s19`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',
    `s20`         BIGINT(20)  NOT NULL DEFAULT 0 COMMENT 'status',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_account` (`account_id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COMMENT 'account_status';

DROP TABLE IF EXISTS `customer_status`;
CREATE TABLE IF NOT EXISTS `customer_status`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'accountId',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgId',

    `s1`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s2`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s3`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s4`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s5`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s6`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s7`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s8`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s9`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s10`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',

    `s11`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s12`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s13`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s14`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s15`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s16`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s17`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s18`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s19`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s20`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_account` (`account_id`, `org_id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT 'customer_status';



DROP TABLE IF EXISTS `company_status`;
CREATE TABLE IF NOT EXISTS `company_status`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgId',

    `s1`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s2`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s3`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s4`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s5`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s6`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s7`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s8`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s9`          BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s10`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',

    `s11`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s12`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s13`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s14`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s15`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s16`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s17`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s18`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s19`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',
    `s20`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT 'status',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_account` (`org_id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT 'company_status';

DROP TABLE IF EXISTS `customer_setting`;
CREATE TABLE IF NOT EXISTS `customer_setting`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'accountId',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgId',

    `namespace`    VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'namespace',
    `data`         TEXT COMMENT 'KV data',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_account` (`account_id`, `org_id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT 'customer_setting';

DROP TABLE IF EXISTS `staff_setting`;
CREATE TABLE IF NOT EXISTS `staff_setting`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`   BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'accountId',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgId',

    `namespace`    VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'namespace',
    `data`         TEXT COMMENT 'KV data',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_account` (`account_id`, `org_id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT 'staff_setting';


DROP TABLE IF EXISTS `company_setting`;
CREATE TABLE IF NOT EXISTS `company_setting`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
    `org_id`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'orgId',

    `namespace`    VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'namespace',
    `data`         TEXT COMMENT 'KV data',

    `version`      INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`  TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `udx_org` (`org_id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
    COMMENT 'company_setting';

