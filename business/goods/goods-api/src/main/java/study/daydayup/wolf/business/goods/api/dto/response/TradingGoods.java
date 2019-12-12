package study.daydayup.wolf.business.goods.api.dto.response;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.entity.Sku;
import study.daydayup.wolf.business.goods.api.vo.Installment;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.framework.layer.api.Response;
import study.daydayup.wolf.model.annotation.column.ForeignKey;
import study.daydayup.wolf.model.type.string.Name;

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
public class TradingGoods extends Response {
    //goodsInfo
    @Min(1)
    private long id;
    @Min(1)
    private long orgId;
    @ForeignKey
    private long categoryId;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum
     */
    private int goodsType;
    /**
     * @see study.daydayup.wolf.business.goods.api.enums.StockTypeEnum
      */
    private int stockType;
    @NotBlank
    private Name name;
    @Min(1)
    private long price;
    @Min(1)
    private int currency;
    @Min(1)
    private int chargeUnit;

    private String mainPic;
    private String code;


    //skuInfo
    private Sku sku;

    //loanInfo
    private Loan loan;
    private List<Installment> installmentList;
}
