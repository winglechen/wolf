package com.wolf.framework.middleware.mq.core.transaction.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;

@Getter
public class TransactionProducerSendFailedException extends SystemException {
    public TransactionProducerSendFailedException(String message) {
        super(110, message);
    }

    public TransactionProducerSendFailedException(long code, String message) {
        super(code, message);
    }
}
