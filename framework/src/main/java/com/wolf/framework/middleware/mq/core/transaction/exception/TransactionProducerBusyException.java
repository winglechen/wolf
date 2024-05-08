package com.wolf.framework.middleware.mq.core.transaction.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;

@Getter
public class TransactionProducerBusyException extends SystemException {
    public TransactionProducerBusyException(String message) {
        super(110, message);
    }

    public TransactionProducerBusyException(long code, String message) {
        super(code, message);
    }
}
