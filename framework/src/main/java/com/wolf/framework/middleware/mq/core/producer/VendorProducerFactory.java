package com.wolf.framework.middleware.mq.core.producer;

import com.wolf.framework.middleware.mq.config.MQProducerConfig;
import com.wolf.framework.middleware.mq.core.MQVendor;
import com.wolf.framework.middleware.mq.vendor.kafka.produce.KafkaProducer;
import com.wolf.framework.middleware.mq.vendor.mock.producer.MockProducer;
import com.wolf.framework.middleware.mq.vendor.rocketmq.producer.RocketProducer;
import com.wolf.framework.middleware.mq.vendor.rocketmq5.producer.Rocket5Producer;

/**
 * @author weixing
 * @since 2022/10/11 20:36
 */
public class VendorProducerFactory {
    public VendorProducer create(MQProducerConfig config) {
        switch (config.getVendorConfig().getVendorType()) {
            case MQVendor.VENDOR_MOCK:
                return new MockProducer(config);
            case MQVendor.VENDOR_KAFKA:
                return new KafkaProducer(config);
            case MQVendor.VENDOR_ROCKET_MQ_5:
                return new Rocket5Producer(config);
            case MQVendor.VENDOR_ROCKET_MQ:
            default:
                return new RocketProducer(config);
        }
    }
}