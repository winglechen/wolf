package study.daydayup.wolf.framework.middleware.mq.core.consumer;

import study.daydayup.wolf.framework.middleware.mq.MQTemplate;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;

import java.util.List;

/**
 * study.daydayup.wolf.framework.middleware.mq.consumer
 *
 * @author Wingle
 * @since 2021/12/16 下午8:09
 **/
public interface Consumer {
    ConsumeResult consume(List<Message> messageList);

    ConsumeResult consume(Message message);
}