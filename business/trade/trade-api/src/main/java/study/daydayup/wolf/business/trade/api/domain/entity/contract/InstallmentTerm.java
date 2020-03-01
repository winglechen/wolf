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
    private Long id;
    private String tradeNo;
    private Long  buyerId;
    private Long  sellerId;

    private Integer installmentNo;
    private Integer installmentType;
    private TradeState state;
    private TradeEvent stateEvent;

    private String relatedTradeNo;

    private Integer period;
    private Integer periodUnit;
    private Integer periodStrategy;
    private BigDecimal percentage;
    private BigDecimal feePercentage;

    private BigDecimal amount;
    private BigDecimal  interest;
    private BigDecimal  handlingFee;

    private LocalDate effectAt;
    private LocalDate dueAt;
    private Integer overdueDays;
    
    private Integer version;

    private TradeStateLog stateLog;
}
