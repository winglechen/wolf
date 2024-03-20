package com.onedot.win.framework.middleware.mq.core.consumer;

import lombok.Data;
import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.framework.middleware.mq.config.MQVendorConfig;
import com.onedot.win.framework.middleware.mq.config.MQConsumerConfig;

/**
 * @author weixing
 * @since 2022/10/11 17:22
 */
@Data
public class ConsumerContext implements Context {
    private MQVendorConfig clientConfig;
    private MQConsumerConfig consumerConfig;
}
