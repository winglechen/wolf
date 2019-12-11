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
@Builder
public class BaseGoods extends Entity {
    @BusinessKey @PrimaryKey
    private long id;
    @ForeignKey @Min(1)
    private long orgId;
    @ForeignKey
    private long categoryId;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    private int goodsType;
    @NotBlank
    private Name name;
    @Min(1)
    private long price;
    @Min(1)
    private int currency;
    private int chargeUnit;


    /**
     * @see GoodsStateEnum
     */
    private int state;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.StockTypeEnum
     */
    private int stockType;


    private String vsPrice;
    private String feature;
    private String mainPic;
    private String mainVideo;
    private String code;
    private String tags;

    private long creator;

    private Loan loan;
    private List<Installment> installments;


    private int version;
}
