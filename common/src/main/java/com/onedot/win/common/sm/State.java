package com.onedot.win.common.sm;

import java.io.Serializable;

/**
 * com.onedot.win.common.framework.layer.domain
 *
 * @author Wingle
 * @since 2018/12/26 11:45 PM
 **/
public interface State extends Serializable {
    int getCode();
    String getName();
}
