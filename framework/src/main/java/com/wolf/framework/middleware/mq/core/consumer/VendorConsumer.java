package com.wolf.framework.middleware.mq.core.consumer;

import com.wolf.framework.middleware.mq.core.MQVendor;

/**
 * @author weixing
 * @since 2022/10/11 17:18
 */
public interface VendorConsumer extends MQVendor {

    void start();

    void suspend();

    void resume();

    void shutdown();
}