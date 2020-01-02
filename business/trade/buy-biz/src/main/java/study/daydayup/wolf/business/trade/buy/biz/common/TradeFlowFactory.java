package study.daydayup.wolf.business.trade.buy.biz.common;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.buy.biz.common.flow.*;
import study.daydayup.wolf.business.trade.api.domain.exception.UnsupportedTradeTypeException;
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
    private BuyFlow buyFlow;
    @Resource
    private GiftFlow giftFlow;
    @Resource
    private GiftReceiveFlow giftReceiveFlow;
    @Resource
    private PeerpayFlow peerpayFlow;
    @Resource
    private PeerpayPayFlow peerpayPayFlow;
    @Resource
    private SecKillFlow secKillFlow;
    @Resource
    private GroupBuyFlow groupBuyFlow;
    @Resource
    private GroupParticipateFlow groupParticipateFlow;
    @Resource
    private FenXiaoFlow fenXiaoFlow;
    @Resource
    private CollectionOrderFlow collectionOrderFlow;
    @Resource
    private LoanProxyFlow loanProxyFlow;
    @Resource
    private RepayOrderFlow repayOrderFlow;
    @Resource
    private LoanOrderFlow loanOrderFlow;
    @Resource
    private LoanContractFlow loanContractFlow;

    public TradeFlow create() {
        return create(TradeTypeEnum.ORDER);
    }

    public TradeFlow create(TradeTypeEnum tradeTypeEnum) {
        TradeFlow tradeFlow = createByTradeType(tradeTypeEnum);
        initTradeFlow(tradeFlow);

        return tradeFlow;
    }

    private TradeFlow createByTradeType(TradeTypeEnum tradeTypeEnum) {
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

    private void initTradeFlow(TradeFlow tradeFlow) {
        tradeFlow.init();
    }
}
