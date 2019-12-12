package study.daydayup.wolf.business.goods.api.entity.goods;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.framework.layer.domain.Entity;
import study.daydayup.wolf.framework.layer.dal.DataVersion;
import study.daydayup.wolf.model.annotation.column.BusinessKey;
import study.daydayup.wolf.model.annotation.column.ForeignKey;
import study.daydayup.wolf.model.annotation.column.PrimaryKey;
import study.daydayup.wolf.model.type.currency.Currency;
import study.daydayup.wolf.model.type.string.Name;
import study.daydayup.wolf.model.type.string.Tags;
import study.daydayup.wolf.model.type.string.URI;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:00 PM
 **/
@Data
public class BaseGoods extends Entity {
    @Builder
    public BaseGoods() {}
    @BusinessKey @PrimaryKey
    protected long id;
    @ForeignKey @Min(1)
    protected long orgId;
    @ForeignKey
    protected long categoryId;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    protected int goodsType;
    @NotBlank
    protected Name name;
    @Min(1)
    protected long price;
    @Min(1)
    protected int currency;
    protected int chargeUnit;


    /**
     * @see GoodsStateEnum
     */
    protected int state;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.StockTypeEnum
     */
    protected int stockType;


    protected String vsPrice;
    protected String feature;
    protected String mainPic;
    protected String mainVideo;
    protected String code;
    protected String tags;

    protected long creator;

    protected int version;
}
