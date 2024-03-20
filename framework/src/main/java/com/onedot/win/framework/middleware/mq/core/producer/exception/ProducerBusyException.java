package com.onedot.win.framework.middleware.mq.core.producer.exception;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class ProducerBusyException extends SystemException {
    public ProducerBusyException(String message) {
        super(110, message);
    }

    public ProducerBusyException(long code, String message) {
        super(code, message);
    }
}
