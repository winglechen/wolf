package study.daydayup.wolf.business.goods.api.entity;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.goods.BaseGoods;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class Goods extends BaseGoods {
    @Builder
    public Goods() {}
    protected String pics;
    protected String skuInfo;
    protected String detail;

    protected List<Sku> skuList;
}
