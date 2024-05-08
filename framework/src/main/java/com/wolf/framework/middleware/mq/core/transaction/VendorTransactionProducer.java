package com.wolf.framework.middleware.mq.core.transaction;

import com.wolf.framework.middleware.mq.core.MQVendor;
import com.wolf.framework.middleware.mq.core.message.Message;
import com.wolf.framework.middleware.mq.core.producer.ProduceResult;

/**
 * com.wolf.framework.middleware.mq.producer
 *
 * @author Wingle
 * @since 2021/12/13 下午9:30
 **/
public interface VendorTransactionProducer extends MQVendor {
    void start();

    void shutdown();

    Transaction beginTransaction(Message message);

    ProduceResult send(Transaction transaction);

    ProduceResult send(TransactionRequest transactionRequest);
}