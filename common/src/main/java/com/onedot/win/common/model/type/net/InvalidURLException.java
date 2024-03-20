package com.onedot.win.common.model.type.net;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

/**
 * com.onedot.win.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:29 下午
 **/
public class InvalidURLException extends SystemException {
    public InvalidURLException(String msg) {
        super(1100, Str.join(
                "invalid url ", msg
        ));
    }
}
