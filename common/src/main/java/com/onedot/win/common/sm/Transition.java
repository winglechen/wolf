package com.onedot.win.common.sm;

import com.onedot.win.common.lang.contract.Context;

/**
 * com.onedot.win.common.sm
 *
 * @author Wingle
 * @since 2020/11/30 1:06 上午
 **/
public interface Transition<S extends State, E extends Event, C extends Context> {
    /**
     *
     * @param context context
     * @return to or null
     */
    S transit(C context);

    S getSource();
    S getTarget();
    E getEvent();
    C getContext();

    Action<S, E, C> getAction();
    Condition<S, E, C> getCondition();
}
