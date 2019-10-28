package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.model.type.string.URI;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class Goods extends BaseGoods {
    private URI[] pics;
    private String skuInfo;
    private String detail;
    private Sku[] skuList;
    private GoodsStatistics statistics;
}
