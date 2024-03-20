package com.onedot.win.framework.middleware.mq.core.consumer.exception;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class ConsumerStartFailedException extends SystemException {
    public ConsumerStartFailedException(String message) {
        super(110, message);
    }

    public ConsumerStartFailedException(long code, String message) {
        super(code, message);
    }
}
