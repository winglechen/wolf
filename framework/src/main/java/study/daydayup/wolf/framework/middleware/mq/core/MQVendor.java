package study.daydayup.wolf.framework.middleware.mq.core;

/**
 * @author weixing
 * @since 2022/10/26 15:00
 */
public interface MQVendor {
    String VENDOR_ROCKET_MQ = "rocketmq";
    String VENDOR_ROCKET_MQ_5 = "rocketmq5";
    String VENDOR_KAFKA = "kafka";

    String getVendorType();

    String getVendorInstanceName();
}
