package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;

/**
 * study.daydayup.wolf.business.trade.api.service.order
 *
 * @author Wingle
 * @since 2019/12/16 11:04 上午
 **/
public interface TradeService {
    void fire(TradeEvent event);
}
