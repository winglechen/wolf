package study.daydayup.wolf.framework.middleware.mq.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weixing
 * @since 2022/10/11 17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MQTransactionProducerConfig extends MQProducerConfig {
    private String topic;

    private int minCheckThreadNum = 1;
    private int maxCheckThreadNum = 1;
    private int maxRequestHold = 2000;
}