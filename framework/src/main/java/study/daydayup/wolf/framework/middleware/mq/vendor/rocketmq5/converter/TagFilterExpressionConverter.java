package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.converter;

import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.middleware.mq.config.MQConsumerConfig;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
public class TagFilterExpressionConverter {

    public static FilterExpression to(MQConsumerConfig config) {
        return to(config.getTags(), "*");
    }

    private static FilterExpression to(Set<String> tags, String defaultTag) {
        if (CollectionUtil.isEmpty(tags)) {
            return new FilterExpression(defaultTag, FilterExpressionType.TAG);
        }

        String expression = tags.stream()
                .filter(StringUtil::notBlank)
                .collect(Collectors.joining("||"));

        return new FilterExpression(expression, FilterExpressionType.TAG);
    }
}
