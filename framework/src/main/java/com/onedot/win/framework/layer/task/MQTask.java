package com.onedot.win.framework.layer.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.middleware.cache.StringRedisUtil;
import com.onedot.win.framework.middleware.mq.core.message.Message;
import com.onedot.win.framework.util.ContextUtil;

/**
 * @author weixing
 * @since 2022/12/20 20:17
 */
@Slf4j
public class MQTask {
    private final static String TASK_IDENTITY_DELIMITER = "@";

    private final StringRedisUtil stringRedisUtil;

    private final Message message;

    private final String[] identity;

    public static String getFirstTaskId(String fullKey) {
        return "mqtask:" + fullKey + "@1";
    }

    public static String getFirstTaskId(String key, String consumerName) {
        return StringUtil.format("mqtask:%s:%s@1", key, consumerName);
    }

    public static String getTaskKey(String firstTaskId) {
        String[] identity = firstTaskId.split(TASK_IDENTITY_DELIMITER);
        return identity[0];
    }

    public static MQTask from(Message message) {
        return new MQTask(message);
    }

    public MQTask(Message message) {
        this.message = message;
        this.identity = getTaskIdentity();
        this.stringRedisUtil = StringRedisUtil.getInstance((RedisTemplate<String, String>) ContextUtil.getBean("redisTemplate", RedisTemplate.class));
    }

    /**
     * check with default redis key ttl = 20m
     * @return boolean
     */
    public boolean shouldExecute() {
        return shouldExecute(60 * 20);
    }

    /**
     * check with customized redis key ttl
     * @param ttl int
     * @return boolean
     */
    public boolean shouldExecute(int ttl) {
        if (identity.length < 2) {
            log.error("unexpected task identity, ignored. msgId={} mqId={} topic={} tag={}",
                message.getId(), message.getMessageContext().getMqId(), message.getTopic(), message.getTag());
            return false;
        }

        String key = identity[0];
        String expectValue = identity[1];
        String nextValue = getNextValue();

        try {
            //log.info("shouldExecute: old={} new={} key={} mqId={}", stringRedisUtil.get(key), nextValue, message.getId(), message.getMessageContext().getMqId());
            boolean result = stringRedisUtil.compareAndSet(key, expectValue, nextValue, ttl);
            // For retry messages, perform a CAS compensatory operation, but ignore the CAS result.
            // Because the original message may not have reached the CAS operation step.
            // We need to ensure that the redis value has been accurately incremented by 1.
            if (!result && message.isRetried()) {
                // if we meet stop execute value, stop executing.
                Long oldValue = Long.parseLong(stringRedisUtil.get(key));
                if (oldValue.equals(0L)) {
                    return false;
                }

                log.info("reconsume message, always should execute. msgId={} mqId={} topic={} tag={} reconsumeTimes={}",
                    message.getId(),
                    message.getMessageContext().getMqId(),
                    message.getTopic(),
                    message.getTag(),
                    message.getMessageContext().getReconsumeTimes());
                return true;
            }

            return result;
        } catch (Exception e) {
            log.error("errors occurred while CAS. msgId={} mqId={} topic={} tag={}",
                message.getId(), message.getMessageContext().getMqId(), message.getTopic(), message.getTag(), e);
            return true;
        }
    }

    /**
     * MQTask consumer should call this method before republish message.
     * @return String
     */
    public String getNextTaskId() {
        String nextValue = identity[1];
        return StringUtil.joinWith(TASK_IDENTITY_DELIMITER, identity[0], nextValue);
    }

    /**
     * For every MQTask executing this method should only be called once.
     * @return Sting
     */
    private String getNextValue() {
        Long expect = Long.parseLong(identity[1]);
        expect++;

        if (expect >= Long.MAX_VALUE) {
            expect = 1L;
        }

        identity[1] = String.valueOf(expect);

        return identity[1];
    }

    private String[] getTaskIdentity() {
        if (null == message.getId() || !message.getId().contains(TASK_IDENTITY_DELIMITER)) {
            return new String[] {};
        }

        return message.getId().split(TASK_IDENTITY_DELIMITER);
    }
}
