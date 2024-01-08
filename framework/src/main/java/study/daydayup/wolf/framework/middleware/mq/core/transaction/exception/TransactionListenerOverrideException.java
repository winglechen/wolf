package study.daydayup.wolf.framework.middleware.mq.core.transaction.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * @author weixing
 * @since 2022/12/12 15:52
 */
public class TransactionListenerOverrideException extends SystemException {
    public TransactionListenerOverrideException(String message) {
        super(110, message);
    }

    public TransactionListenerOverrideException(long code, String message) {
        super(code, message);
    }
}
