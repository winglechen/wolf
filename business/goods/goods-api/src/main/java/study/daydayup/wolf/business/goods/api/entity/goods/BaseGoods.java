package study.daydayup.wolf.business.goods.api.entity.goods;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.common.model.annotation.column.BusinessKey;
import study.daydayup.wolf.common.model.annotation.column.ForeignKey;
import study.daydayup.wolf.common.model.annotation.column.PrimaryKey;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class BaseGoods implements Serializable {
    public BaseGoods() {}
    @BusinessKey @PrimaryKey
    protected Long id;
    @ForeignKey
    protected Long orgId;
    @ForeignKey
    protected Long categoryId;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    protected Integer goodsType;
    @NotBlank
    protected String name;
    @DecimalMin("0.0001")
    protected BigDecimal price;
    protected Integer currency;
    protected Integer chargeUnit;

    /**
     * @see GoodsStateEnum
     */
    protected Integer state;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.StockTypeEnum
     */
    protected Integer stockType;

    protected String vsPrice;
    protected String feature;
    protected String mainPic;
    protected String mainVideo;
    protected String code;
    protected String tags;

    protected Long creator;

    protected Integer version;
}
