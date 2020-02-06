package study.daydayup.wolf.business.trade.api.domain.exception.order;

import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

/**
 * study.daydayup.wolf.business.trade.api.domain.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class UnsupportedStateChangeException extends SystemException {
    public UnsupportedStateChangeException(TradeState state, TradeEvent event) {
        super(160101, Str.join(
                "unsupported trade state change: source state:", state.getCode()
                , "; event: ", event.getClass().getSimpleName()
        ));
    }
}
