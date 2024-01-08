package study.daydayup.wolf.common.sm;

import study.daydayup.wolf.common.lang.contract.Context;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2020/11/30 1:06 上午
 **/
public interface Action<S extends State, E extends Event, C extends Context> {
    void execute(C context);
}
