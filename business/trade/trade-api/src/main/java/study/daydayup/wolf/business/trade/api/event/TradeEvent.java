package study.daydayup.wolf.business.trade.api.event;

import study.daydayup.wolf.common.sm.Event;

/**
 * study.daydayup.wolf.business.trade.api.event
 *
 * @author Wingle
 * @since 2019/10/5 11:14 PM
 **/
public interface TradeEvent extends Event {
    String getTradeNo();
    long getSellerId();
    long getBuyerId();
    int getTradeType();
    int getTradePhase();
}
