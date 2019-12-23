package study.daydayup.wolf.business.trade.api.state.loan;


import study.daydayup.wolf.business.trade.api.state.AbstractTradeState;
import study.daydayup.wolf.business.trade.api.state.TradeState;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/

public class OverdueState extends AbstractTradeState implements TradeState {
    protected int code = 70;
    protected String name = "放款中";
}
