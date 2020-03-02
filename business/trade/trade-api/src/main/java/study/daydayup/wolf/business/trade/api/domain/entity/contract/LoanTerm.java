package study.daydayup.wolf.business.trade.api.domain.entity.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.framework.layer.domain.VO;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.domain.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:54 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class LoanTerm implements VO {
    private String tradeNo;
    private Long  buyerId;
    private Long  sellerId;

    private Integer state;

    @DecimalMin("0.0001")
    private BigDecimal  amount;
    private Integer currency;

    private BigDecimal handlingFee;
    private BigDecimal handlingFeeRate;
    private Integer feePayStrategy;

    private BigDecimal  interest;
    private BigDecimal interestRate;
    private Integer interestUnit;

    private BigDecimal  penalty;
    private BigDecimal penaltyRate;
    private Integer penaltyUnit;

    private Long goodsId;
    private Integer goodsVersion;
    private Integer installmentNum;
    private Integer repayStrategy;
    private Integer prepayStrategy;
    private Integer period;
    private Integer periodUnit;
    private Integer periodStrategy;

    private LocalDateTime createdAt;
}
