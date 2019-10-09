package study.daydayup.wolf.business.trade.buy.domain.factory;

import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.buy.domain.entity.flow.*;
import study.daydayup.wolf.business.trade.api.exception.UnsupportedTradeFlow;

/**
 * study.daydayup.wolf.business.trade.buy.domain.factory
 *
 * @author Wingle
 * @since 2019/10/5 11:32 AM
 **/
public class TradeFlowFactory {
    public static TradeFlow create() {
        return create(TradeTypeEnum.NORMAL);
    }

    public static TradeFlow create(TradeTypeEnum tradeTypeEnum) {
        TradeFlow tradeFlow = createByTradeType(tradeTypeEnum);
        initTradeFlow(tradeFlow);

        return tradeFlow;
    }

    private static TradeFlow createByTradeType(TradeTypeEnum tradeTypeEnum) {
        TradeFlow tradeFlow;
        switch (tradeTypeEnum) {
            case NORMAL:
                return new BuyFlow();
            case GIFT:
                return new GiftFlow();
            case GIFT_RECEIVE:
                return new GiftReceiveFlow();
            case PEERPAY:
                return new PeerpayFlow();
            case PEERPAY_PAY:
                return new PeerpayPayFlow();
            case SECKILL:
                return new SecKillFlow();
            case GROUP_BUY:
                return new GroupBuyFlow();
            case GROUP_PARTICPATE:
                return new GroupParticipateFlow();
            case FENXIAO:
                return new FenXiaoFlow();
            default:
                throw new UnsupportedTradeFlow("No such tradeFlow: " + tradeTypeEnum);
        }
    }

    private static void initTradeFlow(TradeFlow tradeFlow) {
        tradeFlow.init();
        tradeFlow.buildFlow();
    }
}
