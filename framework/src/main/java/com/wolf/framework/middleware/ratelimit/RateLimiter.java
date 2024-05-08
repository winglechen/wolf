package com.wolf.framework.middleware.ratelimit;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import com.wolf.framework.middleware.ratelimit.limiter.KeyLimiter;

import javax.annotation.Resource;

/**
 * com.wolf.framework.middleware.ratelimit
 *
 * @author Wingle
 * @since 2020/10/16 3:11 下午
 **/
@Component
public class RateLimiter {
    @Resource
    private KeyLimiter keyLimiter;

    public void limitByKey(@NonNull LimitConfig config, @NonNull String key) {
        keyLimiter.limit(config, key);
    }
}
