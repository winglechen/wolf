package study.daydayup.wolf.business.trade.api.event.loan;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.event.AbstractTradeEvent;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;

/**
 * study.daydayup.wolf.business.trade.api.event.loan
 *
 * @author Wingle
 * @since 2019/12/16 6:37 下午
 **/
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RefuseEvent extends AbstractTradeEvent implements TradeEvent {
}
