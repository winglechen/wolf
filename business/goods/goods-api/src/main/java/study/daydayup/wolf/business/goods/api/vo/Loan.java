package study.daydayup.wolf.business.goods.api.vo;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.Entity;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

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
    @DecimalMin("0.0001")
    private BigDecimal handlingFeeRate;
    @Min(1)
    private Integer period;
    @DecimalMin("0.0001")
    private BigDecimal interest;
    @DecimalMin("0.0001")
    private BigDecimal penalty;

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
