package study.daydayup.wolf.framework.middleware.mq.vendor.kafka.converter.result;

import org.apache.kafka.clients.producer.RecordMetadata;
import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceResult;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceStateEnum;

/**
 * @author weixing
 * @since 2023/4/18 10:19
 */
public class ProduceResultConverter implements Converter {
    public static ProduceResult from(RecordMetadata metadata, String key) {
        return ProduceResult.builder()
            .state(ProduceStateEnum.SUCCESS)
            .messageId(key)
            .offset(metadata.offset())
            .build();
    }
}