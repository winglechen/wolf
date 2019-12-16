CREATE DATABASE IF NOT EXISTS `wolf_trade` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_trade`;


DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',

    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `buyer_name`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '买家名字',
    `seller_name`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '卖家名字',

    `source`            VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易来源',
    `tags`              VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易标签',

    `trade_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '合同类型',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',
    `related_trade_no`  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '关联交易号',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '合同';

DROP TABLE IF EXISTS `repayment_term`;
CREATE TABLE IF NOT EXISTS `repayment_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `amount`            BIGINT(20) NOT NULL DEFAULT 0 COMMENT '金额',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `repay_strategy`    INT(11) NOT NULL DEFAULT 0 COMMENT '还款策略',
    `prepay_strategy`   INT(11) NOT NULL DEFAULT 0 COMMENT '提前还款策略',
    `handling_fee`      BIGINT(20) NOT NULL DEFAULT 0 COMMENT '手续费',
    `fee_pay_strategy`  INT(11) NOT NULL DEFAULT 0 COMMENT '手续费策略',
    `effect_at`         DATETIME COMMENT '生效时间',
    `duration`          INT(11) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `duration_unit`     INT(11) NOT NULL DEFAULT 0 COMMENT '时长单位',
    `duration_strategy` INT(11) NOT NULL DEFAULT 0 COMMENT '时长策略',
    `interest`          INT(11) NOT NULL DEFAULT 0 COMMENT '利息',
    `interest_unit`     INT(11) NOT NULL DEFAULT 0 COMMENT '利息单位',
    `belated_payment`   INT(11) NOT NULL DEFAULT 0 COMMENT '滞纳金',
    `belated_payment_unit`INT(11) NOT NULL DEFAULT 0 COMMENT '滞纳金单位',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '还款条款';

DROP TABLE IF EXISTS `installment_term`;
CREATE TABLE IF NOT EXISTS `installment_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `installment_no`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期数',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',
    `effect_at`         DATETIME COMMENT '生效时间',
    `related_trade_no`  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '关联交易号',

    `duration`      INT(11) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `percentage`    INT(11) NOT NULL DEFAULT 0 COMMENT '还款比例',
    `fee_percentage`INT(11) NOT NULL DEFAULT 0 COMMENT '手续费比例',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '分期条款';

DROP TABLE IF EXISTS `objects_term`;
CREATE TABLE IF NOT EXISTS `objects_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '标的物条款';

DROP TABLE IF EXISTS `payment_term`;
CREATE TABLE IF NOT EXISTS `payment_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '支付条款';

DROP TABLE IF EXISTS `consign_term`;
CREATE TABLE IF NOT EXISTS `consign_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '物流条款';

DROP TABLE IF EXISTS `assurance_term`;
CREATE TABLE IF NOT EXISTS `assurance_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '保险条款';

DROP TABLE IF EXISTS `trade_memo`;
CREATE TABLE IF NOT EXISTS `trade_memo`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '交易备注';


DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',

    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `buyer_name`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '买家名字',
    `seller_name`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '卖家名字',

    `source`            VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易来源',
    `tags`              VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易标签',

    `trade_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单类型',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',
    `related_trade_no`  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '关联交易号',

    `amount`            BIGINT(20) NOT NULL DEFAULT 0 COMMENT '金额',
    `postage`           BIGINT(20) NOT NULL DEFAULT 0 COMMENT '邮费',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `payment_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',
    `consign_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交付方式',
    `out_trade_no`      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '外部交易号',

    `pay_state`         TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付状态',
    `consign_state`     TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交付状态',
    `completed_state`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '完成状态',
    `dispute_state`     TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '售后状态',

    `paid_at`           DATETIME COMMENT '支付时间',
    `consigned_at`      DATETIME COMMENT '交付时间',
    `dispute_at`        DATETIME COMMENT '售后时间',
    `completed_at`      DATETIME COMMENT '完成时间',

    `version`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`       TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '订单';

DROP TABLE IF EXISTS `order_address`;
CREATE TABLE IF NOT EXISTS `order_address`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `address_type`      TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '地址类型',

    `area_code`        BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '地区码',
    `country`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '国家',
    `province`         VARCHAR(100) NOT NULL DEFAULT '' COMMENT '省',
    `city`             VARCHAR(100) NOT NULL DEFAULT '' COMMENT '市',
    `county`           VARCHAR(100) NOT NULL DEFAULT '' COMMENT '区/县',
    `address`          VARCHAR(100) NOT NULL DEFAULT '' COMMENT '详细地址',
    `consignor`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '交付人',
    `phone`            VARCHAR(30) NOT NULL DEFAULT '' COMMENT '联系电话',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '订单地址';

DROP TABLE IF EXISTS `order_line`;
CREATE TABLE IF NOT EXISTS `order_line`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL COMMENT 'ID',
    `trade_no`          VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `goods_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `sku_id`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `category_id`       MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类目ID',
    `goods_type`        SMALLINT(6) UNSIGNED NOT NULL DEFAULT 0 COMMENT '产品类型',
    `goods_version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `sku`               VARCHAR(1000) NOT NULL DEFAULT '' COMMENT 'sku',
    `goods_name`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '产品名',
    `goods_main_pic`    VARCHAR(100) NOT NULL DEFAULT '' COMMENT '产品主图',

    `sale_price`        BIGINT(20) NOT NULL DEFAULT 0 COMMENT '产品原价',
    `pay_price`         BIGINT(20) NOT NULL DEFAULT 0 COMMENT '支付价格',
    `postage`           BIGINT(20) NOT NULL DEFAULT 0 COMMENT '邮费',
    `currency`          INT(10) NOT NULL DEFAULT 0 COMMENT '币种',
    `charge_unit`        INT(10) NOT NULL DEFAULT 0 COMMENT '单位',
    `quantity`          INT(10) NOT NULL DEFAULT 0 COMMENT '单位',

    `buyer_message`     VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '买家留言',
    `gift_flag`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '礼品标识',

    `consign_state`     TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交付状态',
    `dispute_state`     TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '售后状态',
    `consigned_at`      DATETIME COMMENT '交付时间',
    `dispute_at`        DATETIME COMMENT '售后时间',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '订单项';


DROP TABLE IF EXISTS `order_state_log`;
DROP TABLE IF EXISTS `trade_state_log`;
CREATE TABLE IF NOT EXISTS `trade_state_log`
(
    `id`            INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `trade_no`      VARCHAR(30)          NOT NULL DEFAULT '' COMMENT '订单号',
    `trade_phase`   TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '交易阶段',
    `buyer_id`      BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `source_state`  SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '历史状态',
    `target_state`  SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '更新状态',
    `source_version` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `created_at`    DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '交易状态变更记录';

DROP TABLE IF EXISTS `contract_state_log`;
# CREATE TABLE IF NOT EXISTS `contract_state_log`
# (
#     `id`            INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'id',
#     `contract_no`   VARCHAR(30)          NOT NULL DEFAULT '' COMMENT '合同号',
#     `buyer_id`      BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
#     `seller_id`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',
#     `contract_version` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '历史版本号',
#     `source_state`  SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '历史状态',
#     `target_state`  SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '更新状态',
#     `created_at`    DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     primary key (id)
# ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '交易状态变更记录';

DROP TABLE IF EXISTS `price_change_log`;
CREATE TABLE IF NOT EXISTS `price_change_log`
(
    `id`            INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `order_no`      VARCHAR(30)          NOT NULL DEFAULT '' COMMENT '订单号',
    `contract_no`   VARCHAR(30)          NOT NULL DEFAULT '' COMMENT '合同号',
    `buyer_id`      BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `order_version` INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `source_amount` BIGINT(20) unsigned NOT NULL DEFAULT 0 COMMENT '历史金额',
    `target_amount` BIGINT(20) unsigned NOT NULL DEFAULT 0 COMMENT '更新金额',

    `editor`        BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '编辑人',
    `created_at`    DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '交易状态变更记录';


