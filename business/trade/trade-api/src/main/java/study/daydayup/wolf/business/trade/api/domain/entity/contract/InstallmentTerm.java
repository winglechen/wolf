package study.daydayup.wolf.business.trade.api.domain.entity.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.domain.entity.trade.TradeStateLog;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.framework.layer.domain.VO;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * study.daydayup.wolf.business.trade.api.domain.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class InstallmentTerm implements VO {
    private String tradeNo;
    private String relatedTradeNo;
    private Long  buyerId;
    private Long  sellerId;

    private Integer installmentNo;
    private Integer installmentType;
    private TradeState state;
    private TradeEvent stateEvent;

    private LocalDate effectAt;
    private LocalDate dueAt;
    private Integer overdueDays;
    private Integer effectDays;

    private Integer currency;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private BigDecimal lossAmount;

    private BigDecimal loanAmount;
    private BigDecimal handlingFee;
    private BigDecimal interest;
    private BigDecimal penalty;

    private Integer period;
    private BigDecimal percentage;
    private BigDecimal feePercentage;

    private Integer version;
    private TradeStateLog stateLog;
}
