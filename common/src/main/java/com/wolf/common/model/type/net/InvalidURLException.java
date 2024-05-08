package com.wolf.common.model.type.net;

import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.lang.string.Str;

/**
 * com.wolf.common.sm
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
