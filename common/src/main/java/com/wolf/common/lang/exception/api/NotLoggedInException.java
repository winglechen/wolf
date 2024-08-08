package com.wolf.common.lang.exception.api;

import com.wolf.common.lang.exception.BusinessException;

public class NotLoggedInException extends BusinessException {

    public NotLoggedInException(String message) {
        super(110500, message);
    }

}
