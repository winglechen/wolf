package com.wolf.framework.middleware.mq.core.transaction;

import com.wolf.framework.middleware.mq.core.message.Message;
import com.wolf.framework.middleware.mq.core.producer.ProduceResult;

/**
 * @author weixing
 * @since 2022/12/11 15:25
 */
@Deprecated
public interface TransactionProducer extends TransactionChecker {
    ProduceResult send();

    TransactionResult execute(Message message, Object arg);

    TransactionResult checkTransaction(Message message);
}
