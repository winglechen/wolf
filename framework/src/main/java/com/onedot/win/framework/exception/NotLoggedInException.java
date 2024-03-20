package com.onedot.win.framework.exception;

import com.onedot.win.common.lang.exception.BusinessException;

public class NotLoggedInException extends BusinessException {

    public NotLoggedInException(String message) {
        super(110500, message);
    }

}
