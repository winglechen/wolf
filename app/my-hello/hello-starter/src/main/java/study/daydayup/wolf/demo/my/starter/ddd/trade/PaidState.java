package study.daydayup.wolf.demo.my.starter.ddd.trade;

import study.daydayup.wolf.common.sm.State;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.trade
 *
 * @author Wingle
 * @since 2019/12/25 4:16 下午
 **/
public class PaidState implements State {
    @Override
    public int getCode() {
        return 20;
    }
}
