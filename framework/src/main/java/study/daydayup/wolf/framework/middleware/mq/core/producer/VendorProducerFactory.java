package study.daydayup.wolf.framework.middleware.mq.core.producer;

import study.daydayup.wolf.framework.middleware.mq.config.MQProducerConfig;
import study.daydayup.wolf.framework.middleware.mq.core.MQVendor;
import study.daydayup.wolf.framework.middleware.mq.vendor.kafka.produce.KafkaProducer;
//import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.producer.RocketProducer;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.producer.RocketProducer;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.producer.Rocket5Producer;

/**
 * @author weixing
 * @since 2022/10/11 20:36
 */
public class VendorProducerFactory {
    public VendorProducer create(MQProducerConfig config) {
        switch (config.getVendorConfig().getVendorType()) {
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