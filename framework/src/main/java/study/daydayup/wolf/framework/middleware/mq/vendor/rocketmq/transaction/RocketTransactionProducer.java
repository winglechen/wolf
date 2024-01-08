package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import study.daydayup.wolf.framework.middleware.mq.config.MQTransactionProducerConfig;
import study.daydayup.wolf.framework.middleware.mq.core.MQVendor;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceResult;
import study.daydayup.wolf.framework.middleware.mq.core.producer.exception.ProducerSendFailedException;
import study.daydayup.wolf.framework.middleware.mq.core.producer.exception.ProducerStartFailedException;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.Transaction;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.TransactionRequest;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.VendorTransactionProducer;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.exception.TransactionProducerSendFailedException;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.RocketFactory;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.MessageConverter;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.result.ProduceResultConverter;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.producer
 *
 * @author Wingle
 * @since 2021/12/13 下午9:47
 **/
@Slf4j
public class RocketTransactionProducer implements VendorTransactionProducer {

    //private final MQTransactionProducerConfig config;

    private final TransactionMQProducerCustomized producer;

    public RocketTransactionProducer(MQTransactionProducerConfig config) {
        //this.config = config;
        this.producer = RocketFactory.createTransactionProducer(config);
    }

    @Override
    public String getVendorType() {
        return MQVendor.VENDOR_ROCKET_MQ;
    }

    @Override
    public String getVendorInstanceName() {
        return producer.getInstanceName();
    }

    @Override
    public void start() {
        try {
            producer.start();
        } catch (MQClientException e) {
            log.error("rocket mq transaction producer start failed: ", e);
            throw new ProducerStartFailedException("rocket mq transaction producer start failed");
        }
    }

    @Override
    public void shutdown() {
        try {
            producer.shutdown();
        } catch (Exception e) {
            log.error("errors occurred while close rocket transaction producer.", e);
        }
    }

    @Override
    public Transaction beginTransaction(Message message) {
        Transaction transaction = producer.beginTransaction();
        transaction.setMessage(message);
        return transaction;
    }

    @Override
    public ProduceResult send(Transaction transaction) {
        org.apache.rocketmq.common.message.Message rocketMessage = MessageConverter.toRocketMessage(transaction.getMessage());

        try {
            org.apache.rocketmq.client.producer.SendResult result = producer.sendTransactionMessage(rocketMessage, (RocketTransaction) transaction);
            log.info("rocket mq send transaction message succeeded. msgKeys={} {}", rocketMessage.getKeys(), result);
            return ProduceResultConverter.from(result);
        } catch (MQClientException e) {
            log.error("rocket mq send transaction message failed. msgKeys={}", rocketMessage.getKeys(), e);
            throw new TransactionProducerSendFailedException("rocket mq send transaction message failed");
        }
    }

    @Override
    public ProduceResult send(TransactionRequest transactionRequest) {
        org.apache.rocketmq.common.message.Message rocketMessage = MessageConverter.toRocketMessage(transactionRequest);
        Object arg = null;

        try {
            org.apache.rocketmq.client.producer.SendResult result = producer.sendMessageInTransaction(rocketMessage, arg);
            log.info("rocket mq send transaction message succeeded. msgKeys={} {}", rocketMessage.getKeys(), result);
            return ProduceResultConverter.from(result);
        } catch (MQClientException e) {
            log.error("rocket mq send transaction message failed. msgKeys={}", rocketMessage.getKeys(), e);
            throw new ProducerSendFailedException("rocket mq send transaction message failed");
        }
    }
}