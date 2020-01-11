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
    private Long goodsId;
    private Long orgId;
    //loan add by user
    @Min(1)
    private Integer handlingFeeRate;
    @Min(1)
    private Integer period;
    @Min(1)
    private Integer interest;
    @Min(1)
    private Integer penalty;

    //loan defined in the config
    private Integer interestUnit;
    private Integer penaltyUnit;
    private Integer periodUnit;
    private Integer periodStrategy;
    private Integer repayStrategy;
    private Integer prepayStrategy;
    private Integer amountStrategy;
    private Integer feePayStrategy;
}
