package study.daydayup.wolf.business.trade.api.service.buy;

import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.event.base.PaidEvent;

/**
 * study.daydayup.wolf.business.trade.api.service.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:41 下午
 **/
public interface LoanService {
    void approve(TradeId tradeId);
    void refuse(TradeId tradeId);

    void startLoan(TradeId tradeId);
    void completeLoan(TradeId tradeId);

    void createLoanProxy();
    void prepay();

    void due(TradeId tradeId, Integer installmentNo);
    void overdue(TradeId tradeId, Integer installmentNo);
    void markAsLoss(TradeId tradeId, Integer installmentNo);

    void scanDueLoan();
    void scanOverdueLoan();

    /**
     * subscribe(
     *      loanOrder.paidEvent,
     *      repayOrder.paidEvent
     * )
     * @param event 相关订单支付成功事件
     */
    void subscribePaidEvent(PaidEvent event);
    /**
     * 生成放款订单 + 调用放款接口
     */
    void subscribeLoanEvent();
    void subscribeDueEvent();
    void subscribeOverdueEvent();
    void subscribeLossEvent();
}
