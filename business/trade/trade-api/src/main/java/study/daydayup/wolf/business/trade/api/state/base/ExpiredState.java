package study.daydayup.wolf.business.trade.api.state.base;


import study.daydayup.wolf.business.trade.api.state.AbstractTradeState;
import study.daydayup.wolf.business.trade.api.state.TradeState;

import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/

public class ExpiredState extends AbstractTradeState implements TradeState {
    protected int code = 220;
    private String name = "支付超时";
}
