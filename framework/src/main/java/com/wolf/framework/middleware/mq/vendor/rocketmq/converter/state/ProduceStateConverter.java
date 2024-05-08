package com.wolf.framework.middleware.mq.vendor.rocketmq.converter.state;

import org.apache.rocketmq.client.producer.SendStatus;
import com.wolf.framework.middleware.mq.core.producer.ProduceStateEnum;

/**
 * com.wolf.framework.middleware.mq.adapter.rocketmq.converter
 *
 * @author Wingle
 * @since 2021/12/15 下午9:59
 **/
public class ProduceStateConverter {
    public static ProduceStateEnum fromRocket(SendStatus status) {
        if (status == null) {
            return ProduceStateEnum.FAILURE;
        }

        switch (status) {
            case SEND_OK:
                return ProduceStateEnum.SUCCESS;
            case FLUSH_DISK_TIMEOUT:
            case FLUSH_SLAVE_TIMEOUT:
                return ProduceStateEnum.TIMEOUT;
            case SLAVE_NOT_AVAILABLE:
            default:
                return ProduceStateEnum.FAILURE;

        }
    }
}
