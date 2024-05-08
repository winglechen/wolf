package com.wolf.framework.middleware.mq.core.producer.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;

@Getter
public class ProducerSendFailedException extends SystemException {
    public ProducerSendFailedException(String message) {
        super(110, message);
    }

    public ProducerSendFailedException(long code, String message) {
        super(code, message);
    }
}
