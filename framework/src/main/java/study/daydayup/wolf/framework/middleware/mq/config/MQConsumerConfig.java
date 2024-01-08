package study.daydayup.wolf.framework.middleware.mq.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.api.Config;
import study.daydayup.wolf.framework.middleware.mq.MQTemplate;
import study.daydayup.wolf.framework.middleware.mq.config.exception.MQConfigException;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author weixing
 * @since 2022/10/11 17:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MQConsumerConfig implements Config {

    private MQVendorConfig vendorConfig;

    private MQConsumerGroupConfig groupConfig;

    private String group;

    //private boolean enabled;

    private List<String> topics;

    private Set<String> tags;

    private CheckPoint checkPoint;

    private Class<?> messageClass;

    private Consumer consumer;

    public MQConsumerConfig(Builder builder) {
        vendorConfig = builder.vendorConfig;
        groupConfig = builder.groupConfig;
        group = builder.group;
        topics = builder.topics;
        tags = builder.tags;
        checkPoint = builder.checkPoint;
        consumer = builder.consumer;
    }

    @lombok.Builder
    @Getter
    public static class CheckPoint {
        private Long timestamp;
        private boolean force;
    }

    public static class Builder {
        private final MQTemplate template;

        private MQVendorConfig vendorConfig;
        private MQConsumerGroupConfig groupConfig;
        private String group;
        private List<String> topics;
        private Set<String> tags;
        private CheckPoint checkPoint;
        private Class<?> messageClass;
        private Consumer consumer;

        @Deprecated
        private int minThreadNum;
        @Deprecated
        private int maxThreadNum;

        public Builder(MQTemplate template) {
            this.template = template;
            this.tags = new HashSet<>();
        }

        public Builder vendor(MQVendorConfig config) {
            this.vendorConfig = config;
            return this;
        }

        public Builder consumer(Consumer consumer) {
            this.consumer = consumer;
            return this;
        }

        public Builder group(String group) {
            this.group = group;
            return this;
        }

        public Builder groupConfig(MQConsumerGroupConfig groupConfig) {
            this.group = groupConfig.getGroup();
            this.groupConfig = groupConfig;
            return this;
        }

        public Builder topic(String topic) {
            if (CollectionUtil.isEmpty(this.topics)) {
                this.topics = new ArrayList<>();
            }
            this.topics.add(topic);
            return this;
        }

        public Builder tag(String tag) {
            if (StringUtil.isBlank(tag)) {
                return this;
            }

            this.tags.add(tag);
            return this;
        }

        public Builder tags(Set<String> tags) {
            if (CollectionUtil.isEmpty(tags)) {
                return this;
            }
            this.tags.addAll(tags);
            return this;
        }

        public Builder tags(String... tags) {
            if (tags.length <= 0) {
                return this;
            }
            this.tags.addAll(Arrays.asList(tags));
            return this;
        }

        @Deprecated
        public Builder concurrent(int max) {
            return concurrent(0, max);
        }

        @Deprecated
        public Builder concurrent(int min, int max) {
            if (min > max) {
                throw new MQConfigException("consumer min thread num can not greater than max thread num");
            }

            if (min > 0) {
                minThreadNum = min;
            }

            if (max > 0) {
                maxThreadNum = max;
            }

            return this;
        }

        public Builder checkPoint(CheckPoint checkPoint) {
            this.checkPoint = checkPoint;
            return this;
        }

        public void start() {
            MQConsumerConfig consumerConfig = build();
            template.start(consumerConfig);
        }

        private MQConsumerConfig build() {
            checkConfig();

            if (null == groupConfig) {
                // get yml groupConfig
                groupConfig = template.getMqConfig().getConsumerGroupConfig(group);

                if (minThreadNum > 0) {
                    groupConfig.setMinThreadNum(minThreadNum);
                }
                if (maxThreadNum > 0) {
                    groupConfig.setMaxThreadNum(maxThreadNum);
                }
            }

            if (null == vendorConfig) {
                MQVendorConfig vendorConfig = template.getMqConfig().getVendorConfig(topics.get(0));
                if (null == vendorConfig) {
                    throw new MQConfigException("The vendor config was not found. topic: " + topics.get(0));
                }
                this.vendorConfig = vendorConfig;
            }

            return new MQConsumerConfig(this);
        }

        private void checkConfig() {
            // check topic
            if (CollectionUtil.isEmpty(topics)) {
                throw new MQConfigException("The consumer topic is not specified.");
            }

            // check group
            if (null == group) {
                throw new MQConfigException("The consumer group is not specified.");
            }

            if (null == consumer) {
                throw new MQConfigException("The consumer is not specified.");
            }
        }
    }
}
