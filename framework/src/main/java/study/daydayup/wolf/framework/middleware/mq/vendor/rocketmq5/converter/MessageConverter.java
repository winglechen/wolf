package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.converter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageBuilder;
import org.apache.rocketmq.client.apis.message.MessageView;
import org.apache.rocketmq.client.apis.producer.SendReceipt;

import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.middleware.mq.config.MQDefaultConst;
import study.daydayup.wolf.framework.middleware.mq.core.message.MessageContext;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceRequest;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.Rocket5ClientManager;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
@Slf4j
public class MessageConverter {

    public static final long MAX_DELAY_MILLISECONDS = 86400000L;

    public static Message toRocketMessage(@NonNull study.daydayup.wolf.framework.middleware.mq.core.message.Message message) {
        final ClientServiceProvider provider = Rocket5ClientManager.getInstance().getClientServiceProvider();
        MessageBuilder messageBuilder = provider.newMessageBuilder();

        setMessageFields(messageBuilder, message);
        setUserProperties(messageBuilder, message);

        return messageBuilder.build();
    }

    public static Message toRocketMessage(@NonNull ProduceRequest produceRequest) {
        study.daydayup.wolf.framework.middleware.mq.core.message.Message message = produceRequest.getMessage();

        final ClientServiceProvider provider = Rocket5ClientManager.getInstance().getClientServiceProvider();
        MessageBuilder messageBuilder = provider.newMessageBuilder();

        setMessageFields(messageBuilder, message);
        setUserProperties(messageBuilder, message);

        return messageBuilder.build();
    }

    public static Message toOrderedRocketMessage(@NonNull ProduceRequest produceRequest, @NonNull String messageGroup) {
        study.daydayup.wolf.framework.middleware.mq.core.message.Message message = produceRequest.getMessage();

        final ClientServiceProvider provider = Rocket5ClientManager.getInstance().getClientServiceProvider();
        MessageBuilder messageBuilder = provider.newMessageBuilder();

        setMessageFields(messageBuilder, message);
        setUserProperties(messageBuilder, message);

        messageBuilder.setMessageGroup(messageGroup);

        return messageBuilder.build();
    }

    public static Message toDelayRocketMessage(@NonNull ProduceRequest produceRequest, @NonNull Long delayTimestamp) {
        study.daydayup.wolf.framework.middleware.mq.core.message.Message message = produceRequest.getMessage();

        final ClientServiceProvider provider = Rocket5ClientManager.getInstance().getClientServiceProvider();
        MessageBuilder messageBuilder = provider.newMessageBuilder();

        setMessageFields(messageBuilder, message);
        setUserProperties(messageBuilder, message);

        long currentTimestamp = System.currentTimeMillis();
        if (delayTimestamp - currentTimestamp > MAX_DELAY_MILLISECONDS) {
            messageBuilder.addProperty(MQDefaultConst.MESSAGE_PROPERTY_DELIVERY_TIMESTAMP, String.valueOf(delayTimestamp));
            delayTimestamp = currentTimestamp + MAX_DELAY_MILLISECONDS;

            // Handling the situation where the client clock is faster than the RocketMQ server clock,
            // assuming a time difference of no more than 3 seconds.
            delayTimestamp = delayTimestamp - 3000L;
        }

        messageBuilder.setDeliveryTimestamp(delayTimestamp);

        return messageBuilder.build();
    }

    private static void setMessageFields(MessageBuilder messageBuilder, study.daydayup.wolf.framework.middleware.mq.core.message.Message message) {
        messageBuilder.setTopic(message.getTopic())
            .setBody(message.getBody());

        if (!message.getTags().isEmpty()) {
            messageBuilder.setTag(message.getTags().iterator().next());
        }

        if (StringUtil.notBlank(message.getId())) {
            messageBuilder.setKeys(message.getId());
        }
    }

    private static void setUserProperties(MessageBuilder messageBuilder, study.daydayup.wolf.framework.middleware.mq.core.message.Message message) {
        if (MapUtil.notEmpty(message.getProperties())) {
            for (Map.Entry<String, String> entry : message.getProperties().entrySet()) {
                messageBuilder.addProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void mergeRocketMessageProperty(study.daydayup.wolf.framework.middleware.mq.core.message.Message message, SendReceipt sendReceipt) {
        MessageContext context = message.getMessageContext();
        context.setMqId(sendReceipt.getMessageId().toString());
    }

    public static study.daydayup.wolf.framework.middleware.mq.core.message.Message toTransactionMessage(MessageView messageView) {
        study.daydayup.wolf.framework.middleware.mq.core.message.Message message = new study.daydayup.wolf.framework.middleware.mq.core.message.Message();

        message.setId(StringUtil.joinWith("-", messageView.getKeys()));
        message.setTopic(messageView.getTopic());

        ByteBuffer bodyBuffer = messageView.getBody();
        bodyBuffer.rewind();
        byte[] body = new byte[bodyBuffer.limit()];
        bodyBuffer.get(body);
        message.setBody(body);

        message.setProperties(messageView.getProperties());

        if (messageView.getTag().isPresent()) {
            message.addTag(messageView.getTag().get());
        }

        MessageContext context = message.getMessageContext();
        context.setMqId(messageView.getMessageId().toString());
        context.setReconsumeTimes(messageView.getDeliveryAttempt() - 1);
        context.setTimestamp(messageView.getBornTimestamp());

        return message;
    }

    public static study.daydayup.wolf.framework.middleware.mq.core.message.Message toConsumeMessage(MessageView messageView) {
        study.daydayup.wolf.framework.middleware.mq.core.message.Message message = new study.daydayup.wolf.framework.middleware.mq.core.message.Message();

        message.setId(StringUtil.joinWith("-", messageView.getKeys()));
        message.setTopic(messageView.getTopic());

        ByteBuffer bodyBuffer = messageView.getBody();
        bodyBuffer.rewind();
        byte[] body = new byte[bodyBuffer.limit()];
        bodyBuffer.get(body);
        message.setBody(body);

        message.setProperties(messageView.getProperties());
        //message.setTransactionId(rocketMessage.getTransactionId());
        if (messageView.getTag().isPresent()) {
            message.addTags(messageView.getTag().get());
        }

        MessageContext context = message.getMessageContext();
        context.setMqId(messageView.getMessageId().toString());
        //context.setShardId(rocketMessage.getQueueId());
        //context.setOffset(rocketMessage.getQueueOffset());
        context.setReconsumeTimes(messageView.getDeliveryAttempt() - 1);
        context.setTimestamp(messageView.getBornTimestamp());

        return message;
    }

}
