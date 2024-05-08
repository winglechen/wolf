package com.wolf.framework.middleware.mq.vendor.rocketmq5.converter.state;

import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.middleware.mq.core.consumer.ConsumeStateEnum;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
public class ConsumeStateConverter implements Converter {
    public static ConsumeResult toRocket5(ConsumeStateEnum stateEnum) {
        if (stateEnum == null) {
            return ConsumeResult.FAILURE;
        }

        if (stateEnum == ConsumeStateEnum.SUCCESS) {
            return ConsumeResult.SUCCESS;
        }

        return ConsumeResult.FAILURE;
    }
}
