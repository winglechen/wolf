package com.onedot.win.common.lang.exception.enums;

import com.onedot.win.common.lang.exception.SystemException;

/**
 * com.onedot.win.common.lang.exception
 *
 * @author Wingle
 * @since 2019/9/29 5:47 PM
 **/
public class EnumNotFoundException extends SystemException {
    public EnumNotFoundException(String message) {
        super(500, message);
    }

    public EnumNotFoundException(long code, String message) {
        super(code, message);
    }
}
