package study.daydayup.wolf.business.trade.api.state.base;


import study.daydayup.wolf.business.trade.api.state.AbstractTradeState;
import study.daydayup.wolf.business.trade.api.state.TradeState;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/

public class CompletedState extends AbstractTradeState implements TradeState {
    protected int code = 200;
}
