package study.daydayup.wolf.business.trade.api.service.buy;

import study.daydayup.wolf.business.trade.api.dto.buy.TradeId;
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

    void due(TradeId tradeId);
    void overdue(TradeId tradeId);
    void markAsLoss(TradeId tradeId);

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
}
