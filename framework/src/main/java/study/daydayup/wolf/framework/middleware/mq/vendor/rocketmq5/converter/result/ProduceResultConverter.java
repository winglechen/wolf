package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.converter.result;

import org.apache.rocketmq.client.apis.producer.SendReceipt;

import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceResult;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceStateEnum;

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
