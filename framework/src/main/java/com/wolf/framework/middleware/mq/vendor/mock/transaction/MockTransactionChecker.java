package com.wolf.framework.middleware.mq.vendor.mock.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.apis.producer.TransactionChecker;
import org.apache.rocketmq.client.apis.producer.TransactionResolution;
import com.wolf.framework.middleware.mq.config.MQDefaultConst;
import com.wolf.framework.middleware.mq.config.MQTransactionProducerConfig;
import com.wolf.framework.middleware.mq.core.transaction.TransactionCheckerRegistry;
import com.wolf.framework.middleware.mq.core.transaction.TransactionResult;
import com.wolf.framework.middleware.mq.vendor.rocketmq5.converter.MessageConverter;
import com.wolf.framework.middleware.mq.vendor.rocketmq5.converter.result.TransactionResultConverter;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
@Slf4j
public class MockTransactionChecker implements TransactionChecker {

    /**
     * Not used now, in case need it later
     */
    private final MQTransactionProducerConfig config;

    public MockTransactionChecker(MQTransactionProducerConfig config) {
        this.config = config;
    }

    @Override
    public TransactionResolution check(MessageView messageView) {
        com.wolf.framework.middleware.mq.core.message.Message message = MessageConverter.toTransactionMessage(messageView);

        com.wolf.framework.middleware.mq.core.transaction.TransactionChecker transactionChecker = getTransactionChecker(messageView);
        if (null == transactionChecker) {
            log.error("[MQ] rocketmq5 TransactionChecker not found. msgId={} msgKeys={} topic={} tag={}", messageView.getMessageId(), messageView.getKeys(), messageView.getTopic(), messageView.getTag());
            return TransactionResultConverter.toTransactionState(TransactionResult.COMMIT());
        }

        TransactionResult transactionResult = null;
        try {
            transactionResult = transactionChecker.checkTransaction(message);
        } catch (Exception e) {
            transactionResult = TransactionResult.UNKNOWN();
            log.error("[MQ] rocketmq5 errors occurred while check local transaction state. msgId={} msgKeys={}  topic={} tag={} checker={}",
                messageView.getMessageId(), messageView.getKeys(), messageView.getTopic(), messageView.getTag(),
                transactionChecker.getClass().getSimpleName(), e);
        }

        log.info("[MQ] rocketmq5 check local transaction result={} msgId={} msgKeys={} topic={} tag={}", transactionResult.getState().name(), messageView.getMessageId(), messageView.getKeys(), messageView.getTopic(), messageView.getTag());

        return TransactionResultConverter.toTransactionState(transactionResult);
    }

    private com.wolf.framework.middleware.mq.core.transaction.TransactionChecker getTransactionChecker(MessageView messageView) {
        return TransactionCheckerRegistry.get(messageView.getTopic(), messageView.getTag().orElse(MQDefaultConst.DEFAULT_TAG));
    }

}
