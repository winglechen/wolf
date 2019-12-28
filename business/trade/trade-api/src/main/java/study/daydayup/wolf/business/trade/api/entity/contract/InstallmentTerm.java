package study.daydayup.wolf.business.trade.api.entity.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.entity.trade.TradeStateLog;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;

import java.time.LocalDate;


/**
 * study.daydayup.wolf.business.trade.api.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class InstallmentTerm extends ContractTerm {
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

    protected Long  amount;
    protected Long  interest;
    protected Long  handlingFee;

    protected LocalDate effectAt;
    protected LocalDate dueAt;

    protected Integer version;

    protected TradeStateLog stateLog;
}
