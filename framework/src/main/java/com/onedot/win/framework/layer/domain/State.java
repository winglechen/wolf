package com.onedot.win.framework.layer.domain;

/**
 * com.onedot.win.common.framework.layer.domain
 *
 * @author Wingle
 * @since 2018/12/26 11:45 PM
 **/
public interface State extends com.onedot.win.common.sm.State {
    /**
     * use case:
     * 1, state change need to be logged
     * 2, ...
     *
     * @return StateTypeEnum
     */
    StateTypeEnum getType();
}
