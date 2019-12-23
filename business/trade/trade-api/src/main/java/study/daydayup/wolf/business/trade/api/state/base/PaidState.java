package study.daydayup.wolf.business.trade.api.state.base;

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
public class PaidState extends AbstractTradeState implements TradeState {
    protected int code = 10;
    private String name = "已支付";
}
