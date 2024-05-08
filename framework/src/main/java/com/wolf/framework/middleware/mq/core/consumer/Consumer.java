package com.wolf.framework.middleware.mq.core.consumer;

import com.wolf.framework.middleware.mq.MQTemplate;
import com.wolf.framework.middleware.mq.core.message.Message;

import java.util.List;

/**
 * com.wolf.framework.middleware.mq.consumer
 *
 * @author Wingle
 * @since 2021/12/16 下午8:09
 **/
public interface Consumer {
    ConsumeResult consume(List<Message> messageList);

    ConsumeResult consume(Message message);
}