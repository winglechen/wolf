package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter;

import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.middleware.mq.config.MQConsumerConfig;
import study.daydayup.wolf.framework.middleware.mq.config.MQDefaultConst;
import study.daydayup.wolf.framework.middleware.mq.core.message.Message;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceRequest;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Converter message tags to RocketMQ message tags
 *
 * @author Wingle
 * @since 2021/12/13 下午10:30
 **/
public class TagConverter {

    public static String to(MQConsumerConfig config, String topic) {
        Set<String> tags = config.getTopicTags().get(topic);
        return to(tags, "*");
    }

    public static String to(ProduceRequest produceRequest) {
        return to(produceRequest.getMessage().getTags(), MQDefaultConst.DEFAULT_TAG);
    }

    public static String to(Message message) {
        return to(message.getTags(), MQDefaultConst.DEFAULT_TAG);
    }

    private static String to(Set<String> tags, String defaultTag) {
        if (CollectionUtil.isEmpty(tags)) {
            return defaultTag;
        }

        String expression = tags.stream()
                .filter(StringUtil::notBlank)
                .collect(Collectors.joining(" || "));
        if (StringUtil.notBlank(expression)) {
            return expression;
        }

        return defaultTag;
    }
}
