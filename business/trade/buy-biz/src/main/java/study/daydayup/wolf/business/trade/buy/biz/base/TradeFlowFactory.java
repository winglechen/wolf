package study.daydayup.wolf.business.trade.buy.biz.base;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.buy.biz.base.flow.*;
import study.daydayup.wolf.business.trade.api.domain.exception.UnsupportedTradeTypeException;
import study.daydayup.wolf.business.trade.buy.biz.loan.flow.*;
import study.daydayup.wolf.business.trade.buy.biz.virture.flow.AuditFlow;

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
    private AuditFlow auditFlow;
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
            case REPAY_ORDER:
                return repayOrderFlow;
            case LOAN_ORDER:
                return loanOrderFlow;
            case LOAN_CONTRACT:
                return loanContractFlow;
            case AUDIT_FEE:
                return auditFlow;
            default:
                throw new UnsupportedTradeTypeException(tradeTypeEnum.getCode());
        }
    }

    private void initTradeFlow(TradeFlow tradeFlow) {
        tradeFlow.init();
    }
}
