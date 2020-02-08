package study.daydayup.wolf.business.union.task.service;

/**
 * study.daydayup.wolf.business.union.task.service
 *
 * @author Wingle
 * @since 2020/2/7 10:42 上午
 **/
public interface DailyLoanService {

    void countLoanContract();
    void countLoanContractState();

    /**
     * count loan order
     * 1) loan_count
     * 2) loan_amount
     * 3) first_loan_count
     * 4) first_loan_amount
     */
    void countLoanOrder();

    /**
     * count installment
     * 1) due_count
     * 2) due_amount
     * 3) overdue_count
     * 4) overdue_amount
     * 5) first_overdue_count
     * 6) first_overdue_amount
     * 7) loss_count
     * 8) loss_amount
     */
    void countRepayContract();

    /**
     * count repay order
     * 1) repay_count
     * 2) repay_amount
     */
    void countRepayOrder();
}
