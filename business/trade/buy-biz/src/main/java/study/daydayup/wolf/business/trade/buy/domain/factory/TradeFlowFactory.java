package study.daydayup.wolf.business.trade.buy.domain.factory;

import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.buy.domain.entity.flow.BasicBuyFlow;
import study.daydayup.wolf.business.trade.buy.domain.entity.flow.GiftFlow;
import study.daydayup.wolf.business.trade.buy.domain.entity.flow.PeerpayFlow;
import study.daydayup.wolf.business.trade.buy.domain.entity.flow.TradeFlow;
import study.daydayup.wolf.business.trade.api.exception.UnsupportedTradeFlow;

/**
 * study.daydayup.wolf.business.trade.buy.domain.factory
 *
 * @author Wingle
 * @since 2019/10/5 11:32 AM
 **/
public class TradeFlowFactory {
    public static TradeFlow create() {
        return create(TradeTypeEnum.BASIC_BUY);
    }

    public static TradeFlow create(TradeTypeEnum tradeTypeEnum) {
        TradeFlow tradeFlow;
        switch (tradeTypeEnum) {
            case BASIC_BUY:
                tradeFlow = new BasicBuyFlow();
                break;
            case GIFT:
                tradeFlow = new GiftFlow();
                break;
            case PEERPAY:
                tradeFlow = new PeerpayFlow();
                break;
            default:
                throw new UnsupportedTradeFlow("No such tradeFlow: " + tradeTypeEnum);
        }

        return tradeFlow;
    }
}
