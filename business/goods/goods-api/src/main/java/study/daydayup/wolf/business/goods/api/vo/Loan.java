package study.daydayup.wolf.business.goods.api.vo;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.goods.api.entity
 *
 * @author Wingle
 * @since 2019/12/10 8:48 下午
 **/
@Data
public class Loan implements Entity  {
    private long goodsId;
    //loan add by user
    @Min(1)
    private int handlingFeeRate;
    @Min(1)
    private int period;
    @Min(1)
    private int interest;
    @Min(1)
    private int penalty;

    //loan defined in the config
    private int interestUnit;
    private int penaltyUnit;
    private int periodUnit;
    private int periodStrategy;
    private int repayStrategy;
    private int prepayStrategy;
    private int amountStrategy;
    private int feePayStrategy;
}
