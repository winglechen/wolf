package study.daydayup.wolf.business.goods.api.dto.response;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.Sku;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.framework.layer.api.Response;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.dto.response
 *
 * @author Wingle
 * @since 2019/12/12 10:09 上午
 **/
@Data
public class TradeGoodsResponse implements Response {
    private long id;
    @Min(1)
    private long orgId;
    private long categoryId;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    private int goodsType;
    @NotBlank
    private String name;
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

    private String mainPic;
    private String code;

    private int version;

    private Sku sku;
    private Loan loan;
    private List<Installment> installmentList;

    private long postage = 0;

}
