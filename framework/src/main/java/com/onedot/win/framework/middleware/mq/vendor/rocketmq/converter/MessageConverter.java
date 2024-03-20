package com.onedot.win.framework.middleware.mq.vendor.rocketmq.converter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.middleware.mq.core.message.MessageContext;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceRequest;

import java.util.List;
import java.util.Map;

/**
 * com.onedot.win.framework.middleware.mq.adapter.rocketmq.converter.MessageConverter
 *
 * @author Wingle
 * @since 2021/12/13 下午10:21
 **/
@Slf4j
public class MessageConverter {

    public static Message toRocketMessage(@NonNull com.onedot.win.framework.middleware.mq.core.message.Message message) {
        Message rocketMessage = new Message(message.getTopic(), TagConverter.to(message), message.getBody());

        setMessageId(rocketMessage, message);
        setUserProperties(rocketMessage, message);

        return rocketMessage;
    }

    public static Message toRocketMessage(@NonNull ProduceRequest produceRequest) {
        com.onedot.win.framework.middleware.mq.core.message.Message message = produceRequest.getMessage();
        Message rocketMessage = new Message(message.getTopic(), TagConverter.to(message), message.getBody());

        setMessageId(rocketMessage, message);
        setUserProperties(rocketMessage, message);

        return rocketMessage;
    }

    public static void mergeRocketMessageProperty(com.onedot.win.framework.middleware.mq.core.message.Message message, Message rocketMessage, SendResult sendResult) {
        message.setTransactionId(rocketMessage.getTransactionId());

        MessageContext context = message.getMessageContext();
        context.setMqId(sendResult.getMsgId());
        context.setShardId(sendResult.getMessageQueue().getQueueId());
        context.setOffset(sendResult.getQueueOffset());
    }

    public static com.onedot.win.framework.middleware.mq.core.message.Message toTransactionMessage(Message rocketMessage) {
        com.onedot.win.framework.middleware.mq.core.message.Message message = new com.onedot.win.framework.middleware.mq.core.message.Message();
        message.setId(rocketMessage.getKeys());
        message.setTopic(rocketMessage.getTopic());
        message.setBody(rocketMessage.getBody());
        message.setProperties(rocketMessage.getProperties());
        message.setTransactionId(rocketMessage.getTransactionId());

        return message;
    }

    public static com.onedot.win.framework.middleware.mq.core.message.Message toTransactionMessage(MessageExt rocketMessage) {
        com.onedot.win.framework.middleware.mq.core.message.Message message = new com.onedot.win.framework.middleware.mq.core.message.Message();
        message.setId(rocketMessage.getKeys());
        message.setTopic(rocketMessage.getTopic());
        message.setBody(rocketMessage.getBody());
        message.setProperties(rocketMessage.getProperties());
        message.setTransactionId(rocketMessage.getTransactionId());
        message.addTags(rocketMessage.getTags());

        MessageContext context = message.getMessageContext();
        context.setMqId(rocketMessage.getMsgId());
        context.setShardId(rocketMessage.getQueueId());
        context.setOffset(rocketMessage.getQueueOffset());
        context.setReconsumeTimes(rocketMessage.getReconsumeTimes());

        return message;
    }

    public static com.onedot.win.framework.middleware.mq.core.message.Message toConsumeMessage(MessageExt rocketMessage) {
        com.onedot.win.framework.middleware.mq.core.message.Message message = new com.onedot.win.framework.middleware.mq.core.message.Message();
        message.setId(rocketMessage.getKeys());
        message.setTopic(rocketMessage.getTopic());
        message.setBody(rocketMessage.getBody());
        message.setProperties(rocketMessage.getProperties());
        //message.setTransactionId(rocketMessage.getTransactionId());
        message.addTags(rocketMessage.getTags());

        MessageContext context = message.getMessageContext();
        context.setMqId(rocketMessage.getMsgId());
        context.setShardId(rocketMessage.getQueueId());
        context.setOffset(rocketMessage.getQueueOffset());
        context.setReconsumeTimes(rocketMessage.getReconsumeTimes());

        return message;
    }

    public static List<com.onedot.win.framework.middleware.mq.core.message.Message> toConsumeMessage(List<MessageExt> msgs) {
        return CollectionUtil.to(msgs, MessageConverter::toConsumeMessage);
    }

    private static void setMessageId(Message rocketMessage, com.onedot.win.framework.middleware.mq.core.message.Message message) {
        if (StringUtil.notBlank(message.getId())) {
            rocketMessage.setKeys(message.getId());
        }
    }

    private static <T> void setUserProperties(Message rocketMessage, com.onedot.win.framework.middleware.mq.core.message.Message message) {
        if (MapUtil.notEmpty(message.getProperties())) {
            for (Map.Entry<String, String> entry : message.getProperties().entrySet()) {
                rocketMessage.putUserProperty(entry.getKey(), entry.getValue());
            }
        }
    }

}
