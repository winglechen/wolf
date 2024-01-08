package study.daydayup.wolf.framework.middleware.mq.core.consumer;

import study.daydayup.wolf.framework.middleware.mq.config.MQConsumerConfig;
import study.daydayup.wolf.framework.middleware.mq.core.MQVendor;
import study.daydayup.wolf.framework.middleware.mq.vendor.kafka.consumer.KafkaConsumer;
//import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.consumer.RocketConsumer;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.consumer.RocketConsumer;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.consumer.Rocket5Consumer;

/**
 * @author weixing
 * @since 2022/10/12 17:22
 */
public class VendorConsumerFactory {
    public VendorConsumer create(MQConsumerConfig config) {
        String clientType = config.getVendorConfig().getVendorType();

        switch (clientType) {
            case MQVendor.VENDOR_KAFKA:
                return new KafkaConsumer(config);
            case MQVendor.VENDOR_ROCKET_MQ_5:
                return new Rocket5Consumer(config);
            case MQVendor.VENDOR_ROCKET_MQ:
            default:
                return new RocketConsumer(config);
        }
    }
}
