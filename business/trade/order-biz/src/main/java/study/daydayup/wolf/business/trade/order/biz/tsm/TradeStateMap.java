package study.daydayup.wolf.business.trade.order.biz.tsm;

import study.daydayup.wolf.business.trade.api.state.TradeState;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm
 *
 * @author Wingle
 * @since 2019/12/17 4:24 下午
 **/
public interface TradeStateMap {
    TradeStateMap addState(TradeState state);
    TradeState getByCode(int code);
}
