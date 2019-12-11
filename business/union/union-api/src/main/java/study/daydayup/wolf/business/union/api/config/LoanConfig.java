package study.daydayup.wolf.business.union.api.config;

import lombok.Data;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.business.goods.api.enums.GoodsTypeEnum;
import study.daydayup.wolf.business.goods.api.enums.StockTypeEnum;
import study.daydayup.wolf.common.lang.enums.DurationStrategyEnum;
import study.daydayup.wolf.common.lang.enums.currency.IndianRupeeEnum;
import study.daydayup.wolf.common.lang.enums.finance.FeeStrategyEnum;
import study.daydayup.wolf.common.lang.enums.finance.PrepayStrategyEnum;
import study.daydayup.wolf.common.lang.enums.finance.RepayStrategyEnum;
import study.daydayup.wolf.common.lang.enums.unit.DurationEnum;
import study.daydayup.wolf.common.lang.enums.unit.InterestEnum;

/**
 * study.daydayup.wolf.business.union.api.config
 *
 * @author Wingle
 * @since 2019/12/11 4:16 下午
 **/
@Data
public class LoanConfig {
    private int interestUnit        = InterestEnum.COMPOUND_PER_DAY.getCode();
    private int belatedPaymentUnit  = InterestEnum.RATE_PER_DAY.getCode();
    private int durationUnit        = DurationEnum.DAYS.getCode();
    private int durationStrategy    = DurationStrategyEnum.CLOSE_CLOSE.getCode();
    private int repayStrategy       = RepayStrategyEnum.CONTRACT.getCode();
    private int prepayStrategy      = PrepayStrategyEnum.CONTRACT.getCode();
    private int amountStrategy      = 0;
    private int feePayStrategy      = FeeStrategyEnum.PRE.getCode();

    private int goodsType           = GoodsTypeEnum.LOAN_GOODS.getCode();
    private int stockType           = StockTypeEnum.NO_STOCK.getCode();
    private int categoryId          = 0;
    private byte state              = (byte) GoodsStateEnum.SALABLE.getCode();
    private int currency            = IndianRupeeEnum.RUPEE.getCode();

}
