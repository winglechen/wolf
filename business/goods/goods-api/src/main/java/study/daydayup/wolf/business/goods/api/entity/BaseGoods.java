package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Entity;
import study.daydayup.wolf.model.annotation.key.BusinessKey;
import study.daydayup.wolf.model.annotation.key.ForeignKey;
import study.daydayup.wolf.model.type.money.Money;
import study.daydayup.wolf.model.type.string.Name;
import study.daydayup.wolf.model.type.string.Tags;
import study.daydayup.wolf.model.type.string.URI;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class BaseGoods extends Entity {
    @BusinessKey
    private long goodsId;
    @ForeignKey
    private long orgId;
    @ForeignKey
    private long categoryId;

    private Name name;
    private Money price;

    private Name feature;
    private String vsPrice;
    private URI mainPic;
    private URI mainVideo;
    private Tags tags;
    private String code;
    private int version;

    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    private int goodsType;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.SoldStateEnum
     */
    private int soldState;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.StockTypeEnum
     */
    private int stockType;

}
