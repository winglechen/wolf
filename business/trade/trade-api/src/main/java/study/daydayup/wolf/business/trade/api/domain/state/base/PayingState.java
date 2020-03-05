package study.daydayup.wolf.business.trade.api.domain.state.base;


import study.daydayup.wolf.business.trade.api.domain.state.AbstractTradeState;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;

/**
 * 等待支付结果，不可再次发起支付的状态
 * WaitToPay -> Paying -> x -> Paid
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/

public class PayingState extends AbstractTradeState implements TradeState {
    {
        code = 8;
    }
}
