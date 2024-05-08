package com.wolf.framework.middleware.cache;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weixing
 * @since 2022/12/21 09:49
 */
public class StringRedisUtil {
    private static final Map<String, RedisScript<Boolean>> scripts = new HashMap<>();

    private static volatile StringRedisUtil INSTANCE;

    private RedisTemplate<String, String> redisTemplate;

    static {
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("META-INF/scripts/cas.lua")));
        script.setResultType(Boolean.class);

        scripts.put("CAS", script);
    }

    private StringRedisUtil() {}

    private StringRedisUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static StringRedisUtil getInstance(RedisTemplate<String, String> redisTemplate) {
        if (INSTANCE == null) {
            synchronized (StringRedisUtil.class) {
                if (null == INSTANCE) {
                    INSTANCE = new StringRedisUtil(redisTemplate);
                }
            }
        }
        return INSTANCE;
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public Boolean compareAndSet(String key, String oldValue, String newValue, int ttl) {
        return redisTemplate.execute(scripts.get("CAS"), Collections.singletonList(key), oldValue, newValue, ttl);
    }

    // As of Redis version 6.2.0, this command is regarded as deprecated.
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
