package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import study.daydayup.wolf.framework.exception.WolfException;
import study.daydayup.wolf.framework.middleware.mq.config.MQConsumerConfig;
import study.daydayup.wolf.framework.middleware.mq.core.MQVendor;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.VendorConsumer;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.RocketFactory;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.TagConverter;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.consumer
 *
 * @author Wingle
 * @since 2021/12/16 下午7:58
 **/
@Slf4j
public class RocketConsumer implements VendorConsumer {

    //@Getter
    private final MQConsumerConfig config;

    //@Getter
    private final DefaultMQPushConsumer consumer;

    public RocketConsumer(MQConsumerConfig config) {
        this.config = config;
        this.consumer = RocketFactory.createPushConsumer(config);
    }

    @Override
    public String getVendorType() {
        return MQVendor.VENDOR_ROCKET_MQ;
    }

    @Override
    public String getVendorInstanceName() {
        return consumer.getInstanceName();
    }

    @Override
    public void start() {
        try {
            for (String topic : config.getTopics()) {
                consumer.subscribe(topic, TagConverter.to(config, topic));
            }
            consumer.start();

            Runtime.getRuntime().addShutdownHook(new Thread(consumer::shutdown));
        } catch (Exception e) {
            log.error("[MQ] RocketMQ Consumer start failed. consumerId={}, vendor={} group={}, topics={}, tags={} minThreadNum={}, maxThreadNum={} cause: {}",
                consumer.getInstanceName(),
                config.getVendorConfig().getVendorId(),
                config.getGroup(),
                config.getTopics(),
                config.getTopicTags(),
                config.getGroupConfig().getMinThreadNum(),
                config.getGroupConfig().getMaxThreadNum(),
                e.getMessage(),
                e
            );
            throw new WolfException("RocketMQ Consumer subscribe failed");
        }
    }

    @Override
    public void suspend() {
        consumer.suspend();
        log.info("[MQ] RocketMQ consumer has been suspended. InstanceName={}", consumer.getInstanceName());
    }

    @Override
    public void resume() {
        consumer.resume();
        log.info("[MQ] RocketMQ consumer has resumed consuming messages. InstanceName={}", consumer.getInstanceName());
    }

    @Override
    public void shutdown() {
        log.info("[MQ] RocketMQ consumer is going to shutdown. InstanceName={}", consumer.getInstanceName());
        try {
            consumer.shutdown();
        } catch (Exception e) {
            log.error("[MQ] errors occurred while close rocket consumer. InstanceName={}", consumer.getInstanceName(), e);
        }
    }
}
