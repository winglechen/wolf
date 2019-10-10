package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class Goods extends BaseGoods {
    private Sku[] skuList;
    private GoodsStatistics statistics;
}
