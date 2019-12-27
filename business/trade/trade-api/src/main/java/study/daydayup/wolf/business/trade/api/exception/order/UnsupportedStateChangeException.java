package study.daydayup.wolf.business.trade.api.exception.order;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.business.trade.api.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class UnsupportedStateChangeException extends SystemException {
    public UnsupportedStateChangeException(TradeState state, TradeEvent event) {
        super(160101, Msg.join(
                "unsupported trade state change: source state:", state.getCode()
                , "; event: ", event.getClass().getSimpleName()
        ));
    }
}
