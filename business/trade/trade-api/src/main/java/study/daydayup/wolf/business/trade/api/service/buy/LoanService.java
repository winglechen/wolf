package study.daydayup.wolf.business.trade.api.service.buy;

/**
 * study.daydayup.wolf.business.trade.api.service.buy
 *
 * @author Wingle
 * @since 2019/12/13 3:41 下午
 **/
public interface LoanService {
    void preview();
    void confirm();

    void createLoanOrder();
    void loanByProxy();

    void createDueRepayOrder();
    void handleOverdueRepayOrder();
}
