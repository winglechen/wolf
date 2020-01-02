package study.daydayup.wolf.business.trade.api.domain.event.loan.loan;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.domain.event.AbstractTradeEvent;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.trade.api.domain.event.loan
 *
 * @author Wingle
 * @since 2019/12/16 6:37 下午
 **/
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Deprecated
public class StartLoanEvent extends AbstractTradeEvent implements TradeEvent {
    @NotNull @Min(1)
    private Long amount;
    @NotNull @Min(1)
    private Integer currency;
}
