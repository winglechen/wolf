package study.daydayup.wolf.framework.middleware.mq.core.consumer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.exception.ConsumerSubscriptionConflictException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weixing
 * @since 2022/12/13 18:12
 */
@Slf4j
public class SubscriptionRegistry {
    private static final Map<String/*group*/, Subscription> subscriptionMap = new HashMap<>();

    private SubscriptionRegistry() {}

    public static void register(String group, String topic, String tag) {
        String t = null != tag ? tag : "*";
        register(group, topic, new String[]{t});
    }

    public static void register(String group, String topic, String[] tags) {
        if (!subscriptionMap.containsKey(group)) {
            Subscription subscription = new Subscription();
            subscription.setTopic(topic);
            subscription.setTags(tags);
            subscriptionMap.put(group, subscription);
            return;
        }

        Subscription subscription = get(group);

        if (subscription.getTags().length > 1) {
            Arrays.sort(subscription.getTags());
        }
        if (tags.length > 1) {
            Arrays.sort(tags);
        }

        if (!subscription.getTopic().equals(topic) || !Arrays.equals(subscription.getTags(), tags)) {
            log.error("[MQ] Consumer subscription conflict. registered=[group={} topic={} tags={}] new=[group={} topic={} tags={}]", group, subscription.topic, subscription.tags, group, topic, tags);
            throw new ConsumerSubscriptionConflictException(group, topic, tags);
        }
    }

    public static Subscription get(String group) {
        return subscriptionMap.get(group);
    }

    @Data
    private static class Subscription {
        private String topic;
        private String[] tags;
    }
}
