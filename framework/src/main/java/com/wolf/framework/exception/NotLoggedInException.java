package com.wolf.framework.exception;

import com.wolf.common.lang.exception.BusinessException;

public class NotLoggedInException extends BusinessException {

    public NotLoggedInException(String message) {
        super(110500, message);
    }

}
