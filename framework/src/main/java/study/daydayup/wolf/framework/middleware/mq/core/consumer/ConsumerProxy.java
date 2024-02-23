package study.daydayup.wolf.framework.middleware.mq.core.consumer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;

import java.util.List;

/**
 * @author weixing
 * @since 2024/1/24 15:15
 */
@Slf4j
public class ConsumerProxy extends AbstractConsumer {

    private final String group;

    public ConsumerProxy(String group) {
        this.group = group;
    }

    @Override
    public ConsumeResult consume(Message message) {
        List<Consumer> consumerList = SubscriptionRegistry.getProxiedConsumer(group, message.getTopic(), message.getTag());
        if (null == consumerList) {
            log.error("Can not found matched consumer. group={}, topic={}, tag={}", group, message.getTopic(), message.getTag());
            return ConsumeResult.SUCCESS();
        }

        boolean hasFailure = false;
        for (Consumer consumer : consumerList) {
            ConsumeResult result = consumer.consume(message);
            if (!ConsumeStateEnum.SUCCESS.equals(result.getState())) {
                hasFailure = true;
            }
        }

        if (hasFailure && !ignoreConsumeFailure()) {
            return ConsumeResult.FAILURE();
        }

        return ConsumeResult.SUCCESS();
    }
}