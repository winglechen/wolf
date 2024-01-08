package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.producer;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.producer
 *
 * @author Wingle
 * @since 2021/12/16 下午3:46
 **/
public class OrderSelector implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        int max = mqs.size();
        int index = ThreadLocalRandom.current().nextInt(0, max);

        if (null == arg) {
            return mqs.get(index);
        }

        if (arg instanceof Integer) {
            index = (Integer) arg % max;
        }

        if (arg instanceof Long) {
            index = ((Long) arg).intValue() % max;
        }

        return mqs.get(index);
    }
}
