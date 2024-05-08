package com.wolf.framework.middleware.mq.core.producer.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;

@Getter
public class ProducerStartFailedException extends SystemException {
    public ProducerStartFailedException(String message) {
        super(110, message);
    }

    public ProducerStartFailedException(long code, String message) {
        super(code, message);
    }
}
