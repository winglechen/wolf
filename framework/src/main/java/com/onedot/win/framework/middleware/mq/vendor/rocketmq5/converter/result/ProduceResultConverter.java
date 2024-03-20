package com.onedot.win.framework.middleware.mq.vendor.rocketmq5.converter.result;

import org.apache.rocketmq.client.apis.producer.SendReceipt;

import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceResult;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceStateEnum;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
public class ProduceResultConverter implements Converter {
    public static ProduceResult success(SendReceipt sendReceipt) {
        return ProduceResult.builder()
                .state(ProduceStateEnum.SUCCESS)
                .messageId(sendReceipt.getMessageId().toString())
                .build();
    }
}
