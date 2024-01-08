package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.result;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.rocketmq.client.producer.SendResult;
import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.state.ProduceStateConverter;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceResult;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceStateEnum;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.converter
 *
 * @author Wingle
 * @since 2021/12/14 下午5:23
 **/
public class ProduceResultConverter implements Converter {
    public static ProduceResult from(SendResult result) {
        return ProduceResult.builder()
                .state(ProduceStateConverter.fromRocket(result.getSendStatus()))
                .messageId(result.getMsgId())
                .offset(result.getQueueOffset())
                .build();
    }

    public static ProduceResult from(RecordMetadata metadata, String key) {
        return ProduceResult.builder()
                .state(ProduceStateEnum.SUCCESS)
                .messageId(key)
                .offset(metadata.offset())
                .build();
    }
}
