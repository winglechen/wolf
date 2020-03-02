package study.daydayup.wolf.business.trade.api.domain.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.business.trade.api.domain.entity.trade.TradeStateLog;
import study.daydayup.wolf.framework.layer.domain.VO;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.domain.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RepaymentTerm implements VO {
    private String tradeNo;
    private Long  buyerId;
    private Long  sellerId;

    private Integer state;
    private Integer installmentNum;
    private Integer repayStrategy;
    private Integer prepayStrategy;

    private Integer currency;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private BigDecimal lossAmount;


    private BigDecimal loanAmount;
    private BigDecimal handlingFee;
    private BigDecimal interest;
    private BigDecimal penalty;

    /**
     * installmentNo1, installmentNo2, ...
     */
    private String tags;

    private Integer version;
    private LocalDateTime createdAt;

    private TradeStateLog stateLog;
}
