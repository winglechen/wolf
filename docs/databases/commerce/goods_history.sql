
#2020-06-01
use wolf_goods;

alter table goods
    add column min_price DECIMAL(15, 4) NOT NULL DEFAULT 0.0000 COMMENT 'min价格' after price,
    add column max_price DECIMAL(15, 4) NOT NULL DEFAULT 0.0000 COMMENT 'max价格' after min_price,
    add column `url`  VARCHAR(200) NOT NULL DEFAULT '' COMMENT '外部链接地址' after main_video,

    add index idx_type(`org_id`, `goods_type`, `state`)
;



alter table sku
    add index idx_goods(`goods_id`, `org_id`)
;

alter table goods_loan
    add index idx_goods(`goods_id`, `org_id`)
;

alter table goods_installment
    add index idx_goods(`goods_id`, `org_id`)
;

