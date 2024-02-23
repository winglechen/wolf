package study.daydayup.wolf.framework.middleware.mq.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.framework.config.ThreadConfig;

/**
 * @author weixing
 * @since 2022/11/3 16:06
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MQConsumerGroupConfig extends ThreadConfig {
    protected boolean enabled;
    /**
     * support subscribe multi topics and tags
     */
    protected boolean proxyMode;
    protected String group;

    @Builder.Default
    protected int consumerNum = 1;
    @Builder.Default
    protected int minThreadNum = MQDefaultConst.DEFAULT_CONSUMER_THREAD_MIN;
    @Builder.Default
    protected int maxThreadNum = MQDefaultConst.DEFAULT_CONSUMER_THREAD_MIN;

    protected boolean consumeOrderly = false;
}