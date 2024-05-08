package com.wolf.framework.middleware.mq.core.producer;

import com.wolf.framework.middleware.mq.core.MQVendor;

/**
 * @author weixing
 * @since 2022/10/11 17:18
 */
public interface VendorProducer extends MQVendor {

    void start();

    void shutdown();

    ProduceResult send(ProduceRequest produceRequest);

    void send(ProduceRequest produceRequest, ProduceCallback callback);
}