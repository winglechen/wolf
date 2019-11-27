CREATE DATABASE IF NOT EXISTS `wolf_product` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_product`;

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',
    `category_id`   MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类目ID',
    `product_type`  SMALLINT(6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '产品类型',

    `name`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '产品名',
    `price`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT '价格',
    `currency`      INT(10) NOT NULL DEFAULT 0 COMMENT '币种',
    `chargeUnit`    INT(10) NOT NULL DEFAULT 0 COMMENT '单位',

    `state`         TINYINT(4) NOT NULL DEFAULT 0 COMMENT '产品状态',
    `stock_type`    INT(10) NOT NULL DEFAULT 0 COMMENT '库存类型',

    `vs_price`      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '划线价',
    `feature`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '产品特色',
    `main_pic`      VARCHAR(200) NOT NULL DEFAULT '' COMMENT '产品主图',
    `main_video`    VARCHAR(200) NOT NULL DEFAULT '' COMMENT '产品主视频',

    `code`          VARCHAR(50) NOT NULL DEFAULT '' COMMENT '产品编码',
    `tags`          VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'tags',
    `creator` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建人',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '产品';


DROP TABLE IF EXISTS `product_detail`;
CREATE TABLE IF NOT EXISTS `product_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `pics`          TEXT COMMENT '商品图',
    `sku_info`      TEXT COMMENT 'sku信息',
    `detail`        TEXT COMMENT '产品详情',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '产品详情';


DROP TABLE IF EXISTS `sku`;
CREATE TABLE IF NOT EXISTS `sku`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `product_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `price`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT '价格',
    `currency`      INT(10) NOT NULL DEFAULT 0 COMMENT '币种',
    `chargeUnit`    INT(10) NOT NULL DEFAULT 0 COMMENT '单位',
    `stock_type`    INT(10) NOT NULL DEFAULT 0 COMMENT '库存类型',
    `sku`           VARCHAR(1000) NOT NULL DEFAULT '' COMMENT 'sku',
    `sku_key`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'skuKey',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'sku';

DROP TABLE IF EXISTS `loan_detail`;
CREATE TABLE IF NOT EXISTS `loan_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `product_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `repay_strategy`    INT(10) NOT NULL DEFAULT 0 COMMENT '还款策略',
    `prepay_strategy`   INT(10) NOT NULL DEFAULT 0 COMMENT '提前还款策略',
    `amount_strategy`   INT(10) NOT NULL DEFAULT 0 COMMENT '金额策略',
    `handling_fee_rate` INT(10) NOT NULL DEFAULT 0 COMMENT '手续费',
    `fee_pay_strategy`  INT(10) NOT NULL DEFAULT 0 COMMENT '手续费策略',
    `duration`          INT(10) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `duration_unit`     INT(10) NOT NULL DEFAULT 0 COMMENT '时长单位',
    `duration_strategy` INT(10) NOT NULL DEFAULT 0 COMMENT '时长策略',
    `interest`          INT(10) NOT NULL DEFAULT 0 COMMENT '利息',
    `interest_unit`     INT(10) NOT NULL DEFAULT 0 COMMENT '利息单位',
    `belated_payment`   INT(10) NOT NULL DEFAULT 0 COMMENT '滞纳金',
    `belated_payment_unit`INT(10) NOT NULL DEFAULT 0 COMMENT '滞纳金单位',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'loan';


DROP TABLE IF EXISTS `intallment_detail`;
CREATE TABLE IF NOT EXISTS `installment_detail`
(
    `id`            BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `product_id`    BIGINT(20) UNSIGNED NOT NULL COMMENT '产品ID',
    `org_id`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '组织ID',

    `duration`      INT(10) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `percentage`    INT(10) NOT NULL DEFAULT 0 COMMENT '还款比例',
    `fee_percentage`INT(10) NOT NULL DEFAULT 0 COMMENT '手续费比例',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'installment';

