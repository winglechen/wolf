package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.ConsumeResult;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.Consumer;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.MessageConverter;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.result.ConsumeResultConverter;

import java.util.List;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.consumer
 *
 * @author Wingle
 * @since 2021/12/16 下午8:13
 **/
public class RocketConsumerListener implements MessageListenerConcurrently {

    private final Consumer consumer;

    public RocketConsumerListener(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> rocketMessageList, ConsumeConcurrentlyContext context) {
        if (null == consumer) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        List<Message> messageList = MessageConverter.toConsumeMessage(rocketMessageList);
        ConsumeResult result = consumer.consume(messageList);

        return ConsumeResultConverter.toRocketStatus(result);
    }
}
