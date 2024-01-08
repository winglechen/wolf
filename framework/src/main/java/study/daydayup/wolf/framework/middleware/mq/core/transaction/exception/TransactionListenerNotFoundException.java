package study.daydayup.wolf.framework.middleware.mq.core.transaction.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * @author weixing
 * @since 2022/12/12 15:52
 */
public class TransactionListenerNotFoundException extends SystemException {
    public TransactionListenerNotFoundException(String message) {
        super(110, message);
    }

    public TransactionListenerNotFoundException(String topic, String tag) {
        super(
            StringUtil.format("Transaction listener [topic=%s tag=%s] not found",
                topic,
                tag
            )
        );
    }

    public TransactionListenerNotFoundException(long code, String message) {
        super(code, message);
    }
}
