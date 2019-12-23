package study.daydayup.wolf.business.trade.api.state.loan.collection;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.state.AbstractTradeState;
import study.daydayup.wolf.business.trade.api.state.TradeState;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/
@Data
public class WaitToCollectState extends AbstractTradeState implements TradeState {
    protected int code = 1;
    protected String name = "wait to approve";

}
