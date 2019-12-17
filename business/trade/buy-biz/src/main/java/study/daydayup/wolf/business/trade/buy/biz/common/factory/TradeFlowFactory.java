package study.daydayup.wolf.business.trade.buy.biz.common.factory;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.flow.*;
import study.daydayup.wolf.business.trade.api.exception.UnsupportedTradeTypeException;
import study.daydayup.wolf.business.trade.buy.biz.loan.flow.*;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.domain.factory
 *
 * @author Wingle
 * @since 2019/10/5 11:32 AM
 **/
@Component
public class TradeFlowFactory {
    @Resource
    private static BuyFlow buyFlow;
    @Resource
    private static GiftFlow giftFlow;
    @Resource
    private static GiftReceiveFlow giftReceiveFlow;
    @Resource
    private static PeerpayFlow peerpayFlow;
    @Resource
    private static PeerpayPayFlow peerpayPayFlow;
    @Resource
    private static SecKillFlow secKillFlow;
    @Resource
    private static GroupBuyFlow groupBuyFlow;
    @Resource
    private static GroupParticipateFlow groupParticipateFlow;
    @Resource
    private static FenXiaoFlow fenXiaoFlow;
    @Resource
    private static CollectionOrderFlow collectionOrderFlow;
    @Resource
    private static LoanProxyFlow loanProxyFlow;
    @Resource
    private static RepayOrderFlow repayOrderFlow;
    @Resource
    private static LoanOrderFlow loanOrderFlow;
    @Resource
    private static LoanContractFlow loanContractFlow;

    public static TradeFlow create() {
        return create(TradeTypeEnum.ORDER);
    }

    public static TradeFlow create(TradeTypeEnum tradeTypeEnum) {
        TradeFlow tradeFlow = createByTradeType(tradeTypeEnum);
        initTradeFlow(tradeFlow);

        return tradeFlow;
    }

    private static TradeFlow createByTradeType(TradeTypeEnum tradeTypeEnum) {
        switch (tradeTypeEnum) {
            case ORDER:
                return buyFlow;
            case GIFT:
                return giftFlow;
            case GIFT_RECEIVE:
                return giftReceiveFlow;
            case PEERPAY:
                return peerpayFlow;
            case PEERPAY_PAY:
                return peerpayPayFlow;
            case SECKILL:
                return secKillFlow;
            case GROUP_BUY:
                return groupBuyFlow;
            case GROUP_PARTICIPATE:
                return groupParticipateFlow;
            case FENXIAO:
                return fenXiaoFlow;
            case COLLECTION_ORDER:
                return collectionOrderFlow;
            case LOAN_PROXY:
                return loanProxyFlow;
            case REPAY_ORDER:
                return repayOrderFlow;
            case LOAN_ORDER:
                return loanOrderFlow;
            case LOAN_CONTRACT:
                return loanContractFlow;
            default:
                throw new UnsupportedTradeTypeException(tradeTypeEnum.getCode());
        }
    }

    private static void initTradeFlow(TradeFlow tradeFlow) {
        tradeFlow.init();
    }
}
