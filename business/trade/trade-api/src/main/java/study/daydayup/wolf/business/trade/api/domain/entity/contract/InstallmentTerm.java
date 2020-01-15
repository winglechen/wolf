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
    protected String tradeNo;
    protected Long  buyerId;
    protected Long  sellerId;

    protected Integer installmentNo;
    protected Integer installmentType;
    protected TradeState state;
    protected TradeEvent stateEvent;

    protected String relatedTradeNo;

    protected Integer period;
    protected Integer percentage;
    protected Integer feePercentage;

    protected BigDecimal amount;
    protected BigDecimal  interest;
    protected BigDecimal  handlingFee;

    protected LocalDate effectAt;
    protected LocalDate dueAt;

    protected Integer version;

    protected TradeStateLog stateLog;
}
