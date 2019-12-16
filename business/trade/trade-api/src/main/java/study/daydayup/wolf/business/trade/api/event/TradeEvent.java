package study.daydayup.wolf.business.trade.api.event;

import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;

/**
 * study.daydayup.wolf.business.trade.api.event
 *
 * @author Wingle
 * @since 2019/10/5 11:14 PM
 **/
public interface TradeEvent {
    String getTradeNo();
    long getSellerId();
    long getBuyerId();
    TradeTypeEnum getTradeType();
    TradePhaseEnum getTradePhase();
}
