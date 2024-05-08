package com.wolf.framework.middleware.mq.core.transaction.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;

@Getter
public class TransactionProducerStartFailedException extends SystemException {
    public TransactionProducerStartFailedException(String message) {
        super(110, message);
    }

    public TransactionProducerStartFailedException(long code, String message) {
        super(code, message);
    }
}
