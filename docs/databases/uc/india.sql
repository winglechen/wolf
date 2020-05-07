CREATE DATABASE IF NOT EXISTS `onion`;
USE `onion`;


DROP TABLE IF EXISTS `aadhaar`;
CREATE TABLE `aadhaar`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `aadhaar_no`  varchar(20)         NOT NULL DEFAULT '' COMMENT '用户Aadhaar卡号',
    `uid`         varchar(20)         NOT NULL DEFAULT '',
    `gender`      varchar(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `dob`         varchar(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `name`        varchar(100)        NOT NULL DEFAULT '' COMMENT 'Aadhaar卡姓名',
    `address`     varchar(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `front_side`  varchar(200)        NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`   varchar(200)        NOT NULL DEFAULT '' COMMENT '证件背面图片',
    `vid`         varchar(20)         NOT NULL DEFAULT '',
    `yob`         varchar(10)         NOT NULL DEFAULT '',
    `co`          varchar(100)        NOT NULL DEFAULT '',
    `loc`         varchar(200)        NOT NULL DEFAULT '',
    `vtc`         varchar(50)         NOT NULL DEFAULT '',
    `po`          varchar(50)         NOT NULL DEFAULT '',
    `dist`        varchar(50)         NOT NULL DEFAULT '',
    `subdist`     varchar(50)         NOT NULL DEFAULT '',
    `state`       varchar(50)         NOT NULL DEFAULT '',
    `pc`          varchar(50)         NOT NULL DEFAULT '',
    `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_aadhaar_no` (`aadhaar_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户Aadhaar卡信息表';

DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `name`        varchar(100)        NOT NULL DEFAULT '' COMMENT '联系人姓名',
    `mobile`      varchar(20)         NOT NULL DEFAULT '' COMMENT '联系电话',
    `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户通讯录信息表';

DROP TABLE IF EXISTS `app_installed`;
CREATE TABLE `app_installed`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`   bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`       bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `app_name`     varchar(100)        NOT NULL DEFAULT '' COMMENT '应用名称',
    `package_name` varchar(100)        NOT NULL DEFAULT '' COMMENT '包名',
    `delete_flag`  tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`      int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户手机应用列表信息表';

DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`          bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `bank_name`       varchar(200)        NOT NULL DEFAULT '' COMMENT '银行名称',
    `branch`          varchar(200)        NOT NULL DEFAULT '' COMMENT '银行分行',
    `ifsc`            varchar(100)        NOT NULL DEFAULT '' COMMENT '银行编号',
    `address`         varchar(500)        NOT NULL DEFAULT '' COMMENT '银行地址',
    `bank_no`         varchar(100)        NOT NULL DEFAULT '' COMMENT '银行卡号',
    `relegation_bank` varchar(500)        NOT NULL DEFAULT '' COMMENT '开户行',
    `type`            tinyint(4)          NOT NULL DEFAULT '0' COMMENT '类型: 1储蓄卡 2信用卡',
    `is_loan`         tinyint(2)          NOT NULL DEFAULT '0' COMMENT '是否借款账户 0否  1是',
    `is_pay`          tinyint(2)          NOT NULL DEFAULT '0' COMMENT '是否还款账户 0否  1是',
    `expiration_date` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '有效期',
    `front_side`      varchar(200)        NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`       varchar(200)        NOT NULL DEFAULT '' COMMENT '证件背面图片',
    `delete_flag`     tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`         int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor`     bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户绑定银行卡信息表';

DROP TABLE IF EXISTS `basic_info`;
CREATE TABLE `basic_info`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`     bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`         bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `first_name`     varchar(100)        NOT NULL DEFAULT '' COMMENT '用户姓',
    `middle_name`    varchar(100)        NOT NULL DEFAULT '' COMMENT '用户中间名',
    `last_name`      varchar(100)        NOT NULL DEFAULT '' COMMENT '用户名',
    `address`        varchar(500)        NOT NULL DEFAULT '' COMMENT '住址',
    `residence_city` varchar(100)        NOT NULL DEFAULT '' COMMENT '居住城市',
    `gender`         varchar(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `aadhaar_no`     varchar(20)         NOT NULL DEFAULT '' COMMENT 'Aadhaar号码',
    `pan_no`         varchar(20)         NOT NULL DEFAULT '' COMMENT 'pan号码',
    `email`          varchar(50)         NOT NULL DEFAULT '' COMMENT '邮箱',
    `post_code`      varchar(50)         NOT NULL DEFAULT '' COMMENT '邮编',
    `marital_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '婚姻状况 0未知 1未婚 2已婚 3离婚 4丧偶',
    `occupation`     varchar(100)        NOT NULL DEFAULT '' COMMENT '工作职业',
    `company`        varchar(200)        NOT NULL DEFAULT '' COMMENT '就职企业',
    `industry`       varchar(200)        NOT NULL DEFAULT '' COMMENT '工作行业',
    `delete_flag`    tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`        int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor`    bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`     datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户基础信息表';


DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`     bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`         bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `app_name`       varchar(100)        NOT NULL DEFAULT '' COMMENT '应用名称',
    `app_version`    varchar(20)         NOT NULL DEFAULT '' COMMENT '应用版本号',
    `channel`        varchar(50)         NOT NULL DEFAULT '' COMMENT '应用下载渠道',
    `device_type`    varchar(50)         NOT NULL DEFAULT '' COMMENT '设备类型：unknown, handset, tablet, tv',
    `device_id`      varchar(50)         NOT NULL DEFAULT '' COMMENT '设备唯一标识',
    `mac`            varchar(255)        NOT NULL DEFAULT '' COMMENT '网卡MAC地址',
    `system_version` varchar(20)         NOT NULL DEFAULT '' COMMENT '设备系统版本号',
    `device_model`   varchar(50)         NOT NULL DEFAULT '' COMMENT '设备型号',
    `device_os`      varchar(50)         NOT NULL DEFAULT '' COMMENT '操作系统',
    `mobile`         varchar(15)         NOT NULL DEFAULT '' COMMENT '注册手机号',
    `device_brand`   varchar(50)         NOT NULL DEFAULT '' COMMENT '设备品牌',
    `package_name`   varchar(100)        NOT NULL DEFAULT '' COMMENT '应用包名',
    `root_flag`      tinyint(2)          NOT NULL DEFAULT '0' COMMENT '是否root 0否',
    `delete_flag`    tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`        int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor`    bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`     datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户手机设备信息表';

DROP TABLE IF EXISTS `driving_license`;
CREATE TABLE `driving_license`
(
    `id`                bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`        bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`            bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `dl_no`             varchar(20)         NOT NULL DEFAULT '' COMMENT '驾照编码',
    `cov`               varchar(50)         NOT NULL DEFAULT '',
    `doi`               varchar(100)        NOT NULL DEFAULT '',
    `dob`               varchar(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `bg`                varchar(4)          NOT NULL DEFAULT '',
    `name`              varchar(100)        NOT NULL DEFAULT '' COMMENT '姓名',
    `sdw`               varchar(100)        NOT NULL DEFAULT '',
    `address`           varchar(500)        NOT NULL DEFAULT '' COMMENT '地址',
    `pin`               varchar(20)         NOT NULL DEFAULT '' COMMENT 'pin号',
    `front_side`        varchar(200)        NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`         varchar(200)        NOT NULL DEFAULT '' COMMENT '证件背面图片',
    `issuing_authority` varchar(50)         NOT NULL DEFAULT '' COMMENT '签发授权机构',
    `delete_flag`       tinyint(2)          NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`           int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor`       bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`        datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`        datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_dl_no` (`dl_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户驾照信息表';

DROP TABLE IF EXISTS `emergency_contact`;
CREATE TABLE `emergency_contact`
(
    `id`             bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`     bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`         bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `contact_person` varchar(100)        NOT NULL DEFAULT '' COMMENT '联系人姓名',
    `contact_phone`  varchar(20)         NOT NULL DEFAULT '' COMMENT '联系电话',
    `relationship`   tinyint(2)          NOT NULL DEFAULT '0' COMMENT '1:Father 2:Mother 3:Brother 4:Sister 5:Spouse/Partner 6:Cousin 7:Colleague',
    `delete_flag`    tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`        int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor`    bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`     datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    KEY `idx_account` (`account_id`, `org_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户紧急联系人信息表';

DROP TABLE IF EXISTS `gps`;
CREATE TABLE `gps`
(
    `id`          bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`  bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '组织ID',
    `latitude`    double(10, 4)        NOT NULL DEFAULT '0.0000' COMMENT '纬度',
    `longitude`   double(10, 4)        NOT NULL DEFAULT '0.0000' COMMENT '经度',
    `type`        smallint(4) unsigned NOT NULL DEFAULT '0' COMMENT '记录类型 1登陆注册 2....',
    `delete_flag` tinyint(2) unsigned  NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned     NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                      DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户gps信息表';

DROP TABLE IF EXISTS `liveness_auth_info`;
CREATE TABLE `liveness_auth_info`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `status`      tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '活体认证比对结果',
    `base64_img`  text COMMENT '活体认证图片',
    `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='活体认证记录信息 ';

DROP TABLE IF EXISTS `pan_card`;
CREATE TABLE `pan_card`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `pan_no`      varchar(20)         NOT NULL DEFAULT '' COMMENT '用户pan卡号',
    `dob`         varchar(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `name`        varchar(100)        NOT NULL DEFAULT '' COMMENT 'pan卡姓名',
    `father_name` varchar(100)        NOT NULL DEFAULT '' COMMENT 'pan父亲姓名',
    `front_side`  varchar(200)        NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`   varchar(200)        NOT NULL DEFAULT '' COMMENT '证件背面图片',
    `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_pan_no` (`pan_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户pan卡信息表';

DROP TABLE IF EXISTS `passport`;
CREATE TABLE `passport`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`   bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`       bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `passport_no`  varchar(20)         NOT NULL DEFAULT '' COMMENT '用户passport号',
    `type`         varchar(10)         NOT NULL DEFAULT '' COMMENT 'passport类型',
    `country_code` varchar(20)         NOT NULL DEFAULT '' COMMENT '国际编码',
    `given_name`   varchar(20)         NOT NULL DEFAULT '' COMMENT 'given_name',
    `nationality`  varchar(20)         NOT NULL DEFAULT '' COMMENT '国籍',
    `gender`       varchar(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `dob`          varchar(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `birth_place`  varchar(100)        NOT NULL DEFAULT '' COMMENT '出生地',
    `issue_place`  varchar(100)        NOT NULL DEFAULT '' COMMENT '签发地址',
    `issue_date`   varchar(12)         NOT NULL DEFAULT '' COMMENT '签发日期',
    `expiry_date`  varchar(12)         NOT NULL DEFAULT '' COMMENT '到期日期',
    `father_name`  varchar(100)        NOT NULL DEFAULT '' COMMENT '父亲姓名',
    `mother_name`  varchar(100)        NOT NULL DEFAULT '' COMMENT '母亲姓名',
    `spouse_name`  varchar(100)        NOT NULL DEFAULT '' COMMENT '配偶姓名',
    `address`      varchar(50)         NOT NULL DEFAULT '' COMMENT '地址',
    `front_side`   varchar(200)        NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`    varchar(200)        NOT NULL DEFAULT '' COMMENT '证件背面图片',
    `version`      int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `file_no`      varchar(20)         NOT NULL DEFAULT '' COMMENT '文件编码',
    `last_editor`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`   datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_passport_no` (`passport_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户护照信息表';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`                     bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`             bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`                 bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `channel_id`             bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户渠道id',
    `nickname`               varchar(100)        NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`                 varchar(255)        NOT NULL DEFAULT '' COMMENT '头像',
    `real_name`              varchar(100)        NOT NULL DEFAULT '' COMMENT '真名',
    `mobile`                 varchar(20)         NOT NULL DEFAULT '' COMMENT '手机号',
    `email`                  varchar(100)         NOT NULL DEFAULT '' COMMENT '邮箱',
    `aadhaar_no`             varchar(20)         NOT NULL DEFAULT '' COMMENT 'Aadhaar号码',
    `pan_no`                 varchar(20)         NOT NULL DEFAULT '' COMMENT 'pan号码',
    `gender`                 varchar(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `dob`                    varchar(12)         NOT NULL DEFAULT '' COMMENT '出生日期',
    `credit_amount`          bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '信用额度',
    `tags`                   varchar(200)        NOT NULL DEFAULT '' COMMENT '用户标签，标识用户是否在黑名单等',
    `basic_info_auth_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '基础信息状态 0未认证 1未通过 2通过',
    `liveness_auth_status`   tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '活体认证状态 0未认证 1未通过 2通过',
    `aadhaar_auth_status`    tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'Aadhaar认证状态 0未认证 1未通过 2通过',
    `pan_auth_status`        tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'PAN认证状态 0未认证 1未通过 2通过',
    `passport_auth_status`   tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '护照认证状态 0未认证 1未通过 2通过',
    `dl_auth_status`         tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '驾驶证认证状态 0未认证 1未通过 2通过',
    `voter_card_auth_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '选民证认证状态 0未认证 1未通过 2通过',
    `bank_card_bind_status`  tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '银行卡绑定状态 0未绑定 1绑定',
    `kyc_status`             tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'kyc认证状态 0未绑定 1绑定',
    `version`                int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `delete_flag`            tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `last_editor`            bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`             datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`             datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_account` (`account_id`, `org_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户基础信息表';

DROP TABLE IF EXISTS `user_black`;
CREATE TABLE `user_black`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `first_name`  varchar(100)        NOT NULL DEFAULT '' COMMENT '用户姓',
    `middle_name` varchar(100)        NOT NULL DEFAULT '' COMMENT '用户中间名',
    `last_name`   varchar(100)        NOT NULL DEFAULT '' COMMENT '用户名',
    `real_name`   varchar(100)        NOT NULL DEFAULT '' COMMENT '真名',
    `gender`      varchar(10)         NOT NULL DEFAULT '' COMMENT '性别 male(男) female(女)',
    `aadhaar_no`  varchar(20)         NOT NULL DEFAULT '' COMMENT 'Aadhaar号码',
    `pan_no`      varchar(20)         NOT NULL DEFAULT '' COMMENT 'pan号码',
    `type`        tinyint(2)          NOT NULL DEFAULT '0' COMMENT '类型：0=未知；1=撸贷；2=外部黑名单；3=老赖；',
    `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='黑名单用户信息表';

DROP TABLE IF EXISTS `user_credit`;
CREATE TABLE `user_credit`
(
    `id`                     bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`             bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`                 bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `credit_amount`          bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '信用额度',
    `tags`                   varchar(200)        NOT NULL DEFAULT '' COMMENT '用户标签，标识用户是否在黑名单等',
    `basic_info_auth_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '基础信息状态 0未认证 1未通过 2通过',
    `liveness_auth_status`   tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '活体认证状态 0未认证 1未通过 2通过',
    `aadhaar_auth_status`    tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'Aadhaar认证状态 0未认证 1未通过 2通过',
    `pan_auth_status`        tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'PAN认证状态 0未认证 1未通过 2通过',
    `passport_auth_status`   tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '护照认证状态 0未认证 1未通过 2通过',
    `dl_auth_status`         tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '驾驶证认证状态 0未认证 1未通过 2通过',
    `voter_card_auth_status` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '选民证认证状态 0未认证 1未通过 2通过',
    `bank_card_bind_status`  tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '银行卡绑定状态 0未绑定 1绑定',
    `kyc_status`             tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT 'kyc认证状态 0未绑定 1绑定',
    `version`                int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `delete_flag`            tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `last_editor`            bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`             datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`             datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_account` (`account_id`, `org_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户授信认证信息表';


DROP TABLE IF EXISTS `user_credit_log`;
CREATE TABLE `user_credit_log`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `auth_type`   tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '基础信息1 活体认证2 Aadhaar认证3 PAN认证4 护照认证5 驾驶证认证6 选民证认证7 银行卡绑定8 kyc认证9',
    `status`      tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '认证状态 0未通过 1通过',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `delete_flag` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户授信认证日志信息表';


DROP TABLE IF EXISTS `voter_card`;
CREATE TABLE `voter_card`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `account_id`  bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '账号id',
    `org_id`      bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '组织ID',
    `voter_no`    varchar(20)         NOT NULL DEFAULT '' COMMENT '用户选民证卡号',
    `name`        varchar(100)        NOT NULL DEFAULT '' COMMENT '选民证姓名',
    `father_name` varchar(100)        NOT NULL DEFAULT '' COMMENT '选民证父亲姓名',
    `address`     varchar(500)        NOT NULL DEFAULT '' COMMENT '选民证地址',
    `front_side`  varchar(200)        NOT NULL DEFAULT '' COMMENT '证件正面图片',
    `back_side`   varchar(200)        NOT NULL DEFAULT '' COMMENT '证件背面图片',
    `sign_date`   varchar(12)         NOT NULL DEFAULT '' COMMENT '签发日期',
    `delete_flag` tinyint(2)          NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除，1已删除',
    `version`     int(11) unsigned    NOT NULL DEFAULT '0' COMMENT '版本号',
    `last_editor` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后编辑者',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`  datetime                     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `udx_voter_no` (`voter_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户选民证信息表';


