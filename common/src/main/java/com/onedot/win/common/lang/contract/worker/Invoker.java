package com.onedot.win.common.lang.contract.worker;

/**
 * com.onedot.win.common.lang.contract
 *
 * @author Wingle
 * @since 2020/11/16 5:17 下午
 **/
public interface Invoker<T> {
    void invoke(T context);
}
