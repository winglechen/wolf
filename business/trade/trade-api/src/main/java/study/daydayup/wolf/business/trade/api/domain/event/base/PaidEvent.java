package study.daydayup.wolf.business.trade.api.domain.event.base;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.domain.event.AbstractTradeEvent;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;

/**
 * study.daydayup.wolf.business.trade.api.domain.event
 *
 * @author Wingle
 * @since 2019/10/5 11:15 PM
 **/
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PaidEvent extends AbstractTradeEvent implements TradeEvent {
}
