package com.onedot.win.common.lang.contract.worker;

/**
 * com.onedot.win.framework.layer.converter
 *
 * @author Wingle
 * @since 2020/1/11 2:01 下午
 **/
public interface Parser<Input, Output> {
    Output parse(Input input);
}
