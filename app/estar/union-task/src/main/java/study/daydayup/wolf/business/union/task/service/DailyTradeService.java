package study.daydayup.wolf.business.union.task.service;

/**
 * study.daydayup.wolf.business.union.task.service
 *
 * @author Wingle
 * @since 2020/5/5 4:13 下午
 **/
public interface DailyTradeService {
    void countNewContract();
    void countNewOrder();
    void countTradeStateChange();

}
