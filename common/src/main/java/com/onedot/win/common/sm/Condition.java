package com.onedot.win.common.sm;

import com.onedot.win.common.lang.contract.Context;

/**
 * com.onedot.win.common.sm
 *
 * @author Wingle
 * @since 2020/11/30 1:06 上午
 **/
public interface Condition<S extends State, E extends Event, C extends Context> {
    boolean isSatisfied(C context);
}
