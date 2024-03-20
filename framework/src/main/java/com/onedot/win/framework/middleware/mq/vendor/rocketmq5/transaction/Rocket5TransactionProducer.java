package com.onedot.win.framework.middleware.mq.vendor.rocketmq5.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.producer.Producer;


import org.apache.rocketmq.client.apis.producer.SendReceipt;

import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.exception.WolfException;
import com.onedot.win.framework.middleware.mq.config.MQTransactionProducerConfig;
import com.onedot.win.framework.middleware.mq.core.MQVendor;
import com.onedot.win.framework.middleware.mq.core.message.Message;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceResult;

import com.onedot.win.framework.middleware.mq.core.producer.exception.ProducerStartFailedException;
import com.onedot.win.framework.middleware.mq.core.transaction.Transaction;
import com.onedot.win.framework.middleware.mq.core.transaction.TransactionRequest;

import com.onedot.win.framework.middleware.mq.core.transaction.VendorTransactionProducer;
import com.onedot.win.framework.middleware.mq.core.transaction.exception.TransactionProducerSendFailedException;

import com.onedot.win.framework.middleware.mq.vendor.rocketmq5.Rocket5Factory;

import com.onedot.win.framework.middleware.mq.vendor.rocketmq5.converter.result.ProduceResultConverter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
@Slf4j
public class Rocket5TransactionProducer implements VendorTransactionProducer {

    private final MQTransactionProducerConfig config;

    private Producer producer;

    private static final AtomicInteger TRANSACTION_PRODUCER_ID_SEQ = new AtomicInteger(1);

    private String instanceName;

    public Rocket5TransactionProducer(MQTransactionProducerConfig config) {
        this.config = config;
    }

    @Override
    public String getVendorType() {
        return MQVendor.VENDOR_ROCKET_MQ_5;
    }

    @Override
    public String getVendorInstanceName() {
        return instanceName;
    }

    @Override
    public void start() {
        try {
            this.producer = Rocket5Factory.createTransactionProducer(config);
            this.instanceName = StringUtil.joinWith("-", "trxn-producer", config.getVendorConfig().getVendorId(), TRANSACTION_PRODUCER_ID_SEQ.getAndIncrement());
        } catch (ClientException e) {
            log.error("[MQ] rocketmq5 transaction producer start failed: ", e);
            throw new ProducerStartFailedException("rocketmq5 transaction producer start failed");
        }
    }

    @Override
    public void shutdown() {
        try {
            producer.close();
        } catch (Exception e) {
            log.error("[MQ] errors occurred while close rocketmq5 transaction producer.", e);
        }
    }

    @Override
    public Transaction beginTransaction(Message message) {
        org.apache.rocketmq.client.apis.producer.Transaction transaction;
        try {
            transaction = producer.beginTransaction();
        } catch (Exception e) {
            log.error("[MQ] rocketmq5 transaction begin failed: ", e);
            throw new WolfException("rocketmq5 transaction begin failed.");
        }

        Rocket5Transaction trx = new Rocket5Transaction(transaction);
        trx.setMessage(message);

        return trx;
    }

    @Override
    public ProduceResult send(Transaction transaction) {
        Rocket5Transaction trx = (Rocket5Transaction) transaction;
        org.apache.rocketmq.client.apis.message.Message rocketMessage = trx.getRocketMessage();

        try {
            SendReceipt sendReceipt = producer.send(rocketMessage, trx.getTransaction());
            trx.storeSendReceipt(sendReceipt);
            log.info("[MQ] rocketmq5 send transaction message succeeded. msgKeys={} msgId={}", rocketMessage.getKeys(), sendReceipt.getMessageId());
            return ProduceResultConverter.success(sendReceipt);
        } catch (ClientException e) {
            log.error("[MQ] rocketmq5 send transaction message failed. msgKeys={}", rocketMessage.getKeys(), e);
            throw new TransactionProducerSendFailedException("rocketmq5 send transaction message failed");
        }
    }

    @Override
    public ProduceResult send(TransactionRequest transactionRequest) {
        throw new UnsupportedOperationException("Transaction Request send directly is not supported by rocketmq5");
    }
}