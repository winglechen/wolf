package study.daydayup.wolf.business.goods.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;
import study.daydayup.wolf.framework.layer.dal.DataVersion;
import study.daydayup.wolf.model.annotation.column.BusinessKey;
import study.daydayup.wolf.model.annotation.column.ForeignKey;
import study.daydayup.wolf.model.annotation.column.PrimaryKey;
import study.daydayup.wolf.model.type.currency.Currency;
import study.daydayup.wolf.model.type.string.Name;
import study.daydayup.wolf.model.type.string.Tags;
import study.daydayup.wolf.model.type.string.URI;

import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class BaseGoods extends Entity {
    @BusinessKey @PrimaryKey @NotNull
    private long id;
    @ForeignKey @NotNull
    private long orgId;
    @ForeignKey @NotNull
    private long categoryId;

    @NotNull
    private Name name;
    @NotNull
    private Currency price;

    @NotNull
    private String feature;
    @NotNull
    private String vsPrice;
    @NotNull
    private String code;
    @NotNull
    private URI mainPic;
    @NotNull
    private URI mainVideo;
    @NotNull
    private Tags tags;

    @NotNull
    private DataVersion version;

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
