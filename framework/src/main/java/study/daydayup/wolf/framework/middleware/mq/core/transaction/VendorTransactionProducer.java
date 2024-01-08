package study.daydayup.wolf.framework.middleware.mq.core.transaction;

import study.daydayup.wolf.framework.middleware.mq.core.MQVendor;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceResult;

/**
 * study.daydayup.wolf.framework.middleware.mq.producer
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