package com.wolf.framework.middleware.mq.core.transaction;

import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.framework.middleware.mq.config.MQTransactionProducerConfig;
import com.wolf.framework.middleware.mq.core.MQVendor;
import com.wolf.framework.middleware.mq.vendor.mock.transaction.MockTransactionProducer;
import com.wolf.framework.middleware.mq.vendor.rocketmq.transaction.RocketTransactionProducer;
import com.wolf.framework.middleware.mq.vendor.rocketmq5.transaction.Rocket5TransactionProducer;

/**
 * com.wolf.framework.middleware.mq.producer.factory
 *
 * @author Wingle
 * @since 2021/12/18 下午12:10
 **/
public class VendorTransactionProducerFactory {
    public VendorTransactionProducer create(MQTransactionProducerConfig config) {
        switch (config.getVendorConfig().getVendorType()) {
            case MQVendor.VENDOR_MOCK:
                return new MockTransactionProducer(config);
            case MQVendor.VENDOR_ROCKET_MQ:
                return new RocketTransactionProducer(config);
            case MQVendor.VENDOR_ROCKET_MQ_5:
                return new Rocket5TransactionProducer(config);
            case MQVendor.VENDOR_KAFKA:
            default:
                throw new IllegalArgumentException("transaction producer only support for rocketmq engine");
        }
    }
}