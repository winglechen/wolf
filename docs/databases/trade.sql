CREATE DATABASE IF NOT EXISTS `wolf_trade` DEFAULT CHARACTER SET utf8mb4;
USE `wolf_trade`;

#当日借当日还
DROP TABLE IF EXISTS `contract`;
CREATE TABLE IF NOT EXISTS `contract`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',

    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `buyer_name`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '买家名字',
    `seller_name`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '卖家名字',

    `source`            VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易来源',
    `tags`              VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易标签',

    `trade_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '合同类型',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',
    `related_trade_no`  VARCHAR(32) NOT NULL DEFAULT '' COMMENT '关联交易号',

    `expired_at`        DATETIME COMMENT '过期时间',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_trade_no(`trade_no`),
    INDEX idx_buyer(`buyer_id`, `state`),
    INDEX idx_seller(`seller_id`, `state`, `created_at`),
    INDEX idx_seller_create(`seller_id`, `created_at`),
    INDEX idx_seller_buyer(`seller_id`, `buyer_id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '合同';


DROP TABLE IF EXISTS `loan_term`;
CREATE TABLE IF NOT EXISTS `loan_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `goods_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品ID',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',

    `amount`            DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '借款金额',
    `repay_amount`      DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '实还金额',
    `loss_amount`       DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '资损金额',

    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `installment_num`   TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期数',

    `repay_strategy`    INT(11) NOT NULL DEFAULT 0 COMMENT '还款策略',
    `prepay_strategy`   INT(11) NOT NULL DEFAULT 0 COMMENT '提前还款策略',
    `handling_fee`      DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '手续费',
    `handling_fee_rate` DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '手续费比例',
    `fee_pay_strategy`  INT(11) NOT NULL DEFAULT 0 COMMENT '手续费策略',

    `period`            INT(11) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `period_unit`       INT(11) NOT NULL DEFAULT 0 COMMENT '时长单位',
    `period_strategy`   INT(11) NOT NULL DEFAULT 0 COMMENT '时长策略',
    `interest`          DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '利息',
    `interest_unit`     INT(11) NOT NULL DEFAULT 0 COMMENT '利息单位',
    `penalty`           DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '滞纳金',
    `penalty_unit`      INT(11) NOT NULL DEFAULT 0 COMMENT '滞纳金单位',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_trade_no(`trade_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '借款条款';

# 只读表
DROP TABLE IF EXISTS `repayment_term`;
CREATE TABLE IF NOT EXISTS `repayment_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',

    `repay_strategy`    INT(11) NOT NULL DEFAULT 0 COMMENT '还款策略',
    `prepay_strategy`   INT(11) NOT NULL DEFAULT 0 COMMENT '提前还款策略',

    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `loan_amount`       DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '应还金额',
    `paid_amount`       DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '实还金额',
    `loss_amount`       DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '资损金额',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_trade_no(`trade_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
COMMENT = '还款条款';

# 更改生效时间、state (以下操作都是非必须)
# 生成还款订单 at due_at
# 关闭还款订单 at overdue_at | due_at + one day
# 提前还款：取消还款订单 + 更新state
# 还款成功：更新state
DROP TABLE IF EXISTS `installment_term`;
CREATE TABLE IF NOT EXISTS `installment_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `installment_no`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期数',
    `installment_type`  TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分期类型',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',
    `related_trade_no`  VARCHAR(32) NOT NULL DEFAULT '' COMMENT '关联交易号',

    `effect_at`         DATE COMMENT '生效时间',
    `due_at`            DATE COMMENT '到期时间',

    `amount`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '金额',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `paid_amount`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '金额',
    `loss_amount`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '金额',

    `interest`          DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '利息金额',
    `handling_fee`      DECIMAL(15, 4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '手续费',

    `period`            INT(11) NOT NULL DEFAULT 0 COMMENT '借款时长',
    `percentage`        DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '还款比例',
    `fee_percentage`    DECIMAL(8, 4) NOT NULL DEFAULT 0 COMMENT '手续费比例',

    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    INDEX idx_trade_no(`trade_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '运费条款';

DROP TABLE IF EXISTS `objects_term`;
CREATE TABLE IF NOT EXISTS `objects_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
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
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
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
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
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
DROP TABLE IF EXISTS `postage_term`;
CREATE TABLE IF NOT EXISTS `postage_term`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
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
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',


    `version`     INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor` BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`  DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_trade_no(`trade_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '交易备注';

#tags: firstOrder、installment_no
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',

    `buyer_id`          BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `buyer_name`        VARCHAR(100) NOT NULL DEFAULT '' COMMENT '买家名字',
    `seller_name`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '卖家名字',

    `source`            VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易来源',
    `tags`              VARCHAR(200) NOT NULL DEFAULT '' COMMENT '交易标签',

    `trade_type`        TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单类型',
    `state`             TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交易状态',
    `related_trade_no`  VARCHAR(32) NOT NULL DEFAULT '' COMMENT '关联交易号',

    `amount`            DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '金额',
    `postage`           DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '邮费',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',

    `payment_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',
    `consign_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交付方式',
    `out_trade_no`      VARCHAR(50) NOT NULL DEFAULT '' COMMENT '外部交易号',

    `expired_at`        DATETIME COMMENT '过期时间',

    `version`           INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '版本号',
    `delete_flag`       TINYINT(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`       BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    UNIQUE INDEX udx_trade_no(`trade_no`),
    INDEX idx_buyer(`buyer_id`, `state`),
    INDEX idx_seller(`seller_id`, `state`, `created_at`),
    INDEX idx_seller_type(`seller_id`, `trade_type`, `state`, `created_at`),
    INDEX idx_seller_type_created(`seller_id`, `trade_type`, `created_at`, `state`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '订单';

DROP TABLE IF EXISTS `order_address`;
CREATE TABLE IF NOT EXISTS `order_address`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
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
    UNIQUE INDEX udx_trade_no(`trade_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '订单地址';

DROP TABLE IF EXISTS `order_line`;
CREATE TABLE IF NOT EXISTS `order_line`
(
    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT  COMMENT 'ID',
    `trade_no`          VARCHAR(32) NOT NULL DEFAULT '' COMMENT '交易号',
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
    `goods_code`        VARCHAR(50) NOT NULL DEFAULT '' COMMENT '产品编码',

    `sale_price`        DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '产品原价',
    `pay_price`         DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '支付价格',
    `postage`           DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '邮费',
    `currency`          INT(10) NOT NULL DEFAULT 0 COMMENT '币种',
    `charge_unit`       INT(10) NOT NULL DEFAULT 0 COMMENT '单位',
    `quantity`          INT(10) NOT NULL DEFAULT 0 COMMENT '单位',

    `buyer_memo`        VARCHAR(1000) NOT NULL DEFAULT '' COMMENT '买家留言',
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
    INDEX idx_trade_no(`trade_no`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4
    COMMENT = '订单项';

#首逾
DROP TABLE IF EXISTS `trade_state_log`;
CREATE TABLE IF NOT EXISTS `trade_state_log`
(
    `id`                INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `trade_no`          VARCHAR(32)          NOT NULL DEFAULT '' COMMENT '交易号',
    `related_trade_no`  VARCHAR(32)          NOT NULL DEFAULT '' COMMENT '关联交易号',
    `trade_type`        TINYINT(4) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '交易类型',

    `buyer_id`          BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`         BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',
    `source_state`      SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '历史状态',
    `target_state`      SMALLINT(6) unsigned NOT NULL DEFAULT 0 COMMENT '更新状态',

    `amount`            DECIMAL(15, 4) NOT NULL DEFAULT 0 COMMENT '金额',
    `currency`          INT(11) NOT NULL DEFAULT 0 COMMENT '币种',
    `payment_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式',
    `consign_method`    TINYINT(4) UNSIGNED NOT NULL DEFAULT 0 COMMENT '交付方式',

    `tags`              VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '交易标签',
    `source`            VARCHAR(200)         NOT NULL DEFAULT '' COMMENT '交易来源',

    `source_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `target_version`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新版本号',
    `editor`            BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`        DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_trade_no(`trade_no`),
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '交易状态变更记录';

DROP TABLE IF EXISTS `price_change_log`;
CREATE TABLE IF NOT EXISTS `price_change_log`
(
    `id`            INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `trade_no`      VARCHAR(32)          NOT NULL DEFAULT '' COMMENT '订单号',
    `line_id`       BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '订单项ID',

    `buyer_id`      BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '买家ID',
    `seller_id`     BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '卖家ID',

    `source_version`INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '历史版本号',
    `target_version`INT(11) UNSIGNED     NOT NULL DEFAULT 0 COMMENT '更新版本号',

    `source_amount` DECIMAL(15, 4) unsigned NOT NULL DEFAULT 0 COMMENT '历史金额',
    `target_amount` DECIMAL(15, 4) unsigned NOT NULL DEFAULT 0 COMMENT '更新金额',

    `editor`        BIGINT(20) UNSIGNED  NOT NULL DEFAULT 0 COMMENT '编辑人',
    `created_at`    DATETIME             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_trade_no(`trade_no`),
    primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COMMENT = '交易状态变更记录';


