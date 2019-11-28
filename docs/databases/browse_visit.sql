DROP TABLE IF EXISTS `visit_log`;
CREATE TABLE IF NOT EXISTS `visit_log`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `user_id`      BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '用户ID',
    `mobile`       VARCHAR(20)         NOT NULL DEFAULT '' COMMENT '用户手机号',
    `channel_id`   BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '渠道',
    `channel_flag` VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '渠道标识',
    `uuid`         VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '用户唯一标识',
    `device_info`  VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '设备信息',
    `os_type`      TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '设备类型；0：安卓；1：iOS',
    `extra_id`     BIGINT(20)          NOT NULL DEFAULT 0 COMMENT '额外Id，根据场景值，可存放产品Id、专题Id、广告图Id',
    `ip`           VARCHAR(100)        NOT NULL DEFAULT '' COMMENT '访问IP',
    `referrer`     VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '访问来源',
    `ua`           VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '访问环境',
    `page`         VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '访问页面',
    `is_deduction` TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否扣量 0否 1是',
    `scene`        TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '场景值  100:用户进入H5页面; 200:用户H5登录; 300:用户进入下载页;400:App被打开;500:App登录;600:App退出登录;700:App3个底部TAB（每个TAB有独立标识）;800:App点击banner广告（banner有唯一标识）;910:App点击主推产品（带主推标识和产品ID;920:App点击热推产品（带热推标识和产品ID);930:App点击产品专题（专题唯一标识);1000:App点击贷款页产品（产品ID）;1100:App贷款页翻页（记页码，统计访问深度）;1200:App内webview成功加载产品页面',
    `geo`          VARCHAR(255)        NOT NULL DEFAULT '' COMMENT 'gps位置',
    `log_type`     TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录类型；0:h5 1:App 2:计费记录',
    `ymd_str`      INT(9)              NOT NULL DEFAULT 0 COMMENT '年月日；方便后期按日统计',
    `delete_flag`  TINYINT(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0未删除，1已删除',
    `last_editor`  BIGINT(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后编辑者',
    `created_at`   DATETIME            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4 COMMENT = '用户流量访问记录';

