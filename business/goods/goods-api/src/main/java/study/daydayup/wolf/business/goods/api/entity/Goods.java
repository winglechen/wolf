package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.goods.api.entity.goods.BaseGoods;

import java.io.Serializable;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class Goods extends BaseGoods implements Serializable {
    public Goods() {}
    protected String pics;
    protected String skuInfo;
    protected String extInfo;
    protected String detail;

    protected List<Sku> skuList;
}
