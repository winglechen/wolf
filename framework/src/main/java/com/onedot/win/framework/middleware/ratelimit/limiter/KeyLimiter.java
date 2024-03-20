package com.onedot.win.framework.middleware.ratelimit.limiter;

import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import com.onedot.win.common.lang.enums.unit.PeriodEnum;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.common.util.time.DateUtil;
import com.onedot.win.framework.middleware.ratelimit.LimitConfig;
import com.onedot.win.framework.middleware.ratelimit.RequestEvent;
import com.onedot.win.framework.middleware.ratelimit.RequestLimitedException;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * com.onedot.win.framework.middleware.ratelimit.limiter
 *
 * @author Wingle
 * @since 2020/10/17 10:16 上午
 **/
@Component
public class KeyLimiter {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void limit(@NonNull LimitConfig config, @NonNull String key) {
        RequestEvent event = formatEvent(key);
        checkLimit(config, event);
    }

    private RequestEvent formatEvent(String key) {
        return RequestEvent.builder()
                .key(key)
                .requestAt(LocalDateTime.now())
                .build();
    }

    private void checkLimit(LimitConfig config, RequestEvent event) {
        checkSecondLimit(config, event);
        checkMinuteLimit(config, event);
        checkHourLimit(config, event);
        checkDayLimit(config, event);
        checkWeekLimit(config, event);
        checkMonthLimit(config, event);
        checkYearLimit(config, event);
    }

    private void checkSecondLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerSecond(), event, PeriodEnum.SECONDS);
    }

    private void checkMinuteLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerMinute(), event, PeriodEnum.MINUTES);
    }

    private void checkHourLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerHour(), event, PeriodEnum.HOURS);
    }

    private void checkDayLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerDay(), event, PeriodEnum.DAYS);
    }

    private void checkWeekLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerWeek(), event, PeriodEnum.WEEKS);
    }

    private void checkMonthLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerMonth(), event, PeriodEnum.MONTHS);
    }

    private void checkYearLimit(LimitConfig config, RequestEvent event) {
        executeChecking(config.getMaxRequestPerYear(), event, PeriodEnum.YEARS);
    }

    private void executeChecking(Integer limit, RequestEvent event, PeriodEnum unit) {
        if (null == limit) {
            return;
        }

        String key = formatKey(event, unit);
        Long count = getRedisValueOps().increment(key);
        if (null == count) {
            throw new IllegalArgumentException("KeyLimiter not work in pipe line or transaction mode");
        }

        if (count == 1L) {
            setExpireTime(key, unit);
            return;
        }

        if (count > limit) {
            getRedisValueOps().decrement(key);
            throw new RequestLimitedException();
        }
    }

    private ValueOperations<String, Object> getRedisValueOps() {
        return redisTemplate.opsForValue();
    }

    private String formatKey(RequestEvent event, PeriodEnum unit) {
        return StringUtil.join(RequestEvent.PREFIX, event.getKey(), getTimeString(event, unit));
    }

    private void setExpireTime(String key, PeriodEnum unit) {
        switch (unit) {
            case SECONDS:
                redisTemplate.expire(key, 5, TimeUnit.SECONDS);
                return;
            case MINUTES:
                redisTemplate.expire(key, 65, TimeUnit.SECONDS);
                return;
            case HOURS:
                redisTemplate.expire(key, 60*60+5, TimeUnit.SECONDS);
                return;
            case DAYS:
                redisTemplate.expire(key, 24*60*60+5, TimeUnit.SECONDS);
                return;
            case WEEKS:
                redisTemplate.expire(key, 7*24*60*60+5, TimeUnit.SECONDS);
                return;
            case MONTHS:
                redisTemplate.expire(key, 30*24*60*60+5, TimeUnit.SECONDS);
                return;
            case YEARS:
                redisTemplate.expire(key, 366*24*60*60+5, TimeUnit.SECONDS);
                return;
            default:
                throw new IllegalArgumentException("unsupported keyLimiter time unit");
        }
    }

    private String getTimeString(RequestEvent event, PeriodEnum unit) {
        String ts;
        switch (unit) {
            case SECONDS:
                ts = DateUtil.asString(event.getRequestAt(), "yyyyMMddHHmmss");
                break;
            case MINUTES:
                ts = DateUtil.asString(event.getRequestAt(), "yyyyMMddHHmm");
                break;
            case HOURS:
                ts = DateUtil.asString(event.getRequestAt(), "yyyyMMddHH");
                break;
            case DAYS:
                ts = DateUtil.asString(event.getRequestAt(), "yyyyMMdd");
                break;
            case WEEKS:
                ts = StringUtil.join(DateUtil.asString(event.getRequestAt(), "yyyy"), DateUtil.getWeek(event.getRequestAt()));
                break;
            case MONTHS:
                ts = DateUtil.asString(event.getRequestAt(), "yyyyMM");
                break;
            case YEARS:
                ts = DateUtil.asString(event.getRequestAt(), "yyyy");
                break;
            default:
                throw new IllegalArgumentException("unsupported keyLimiter time unit");
        }

        return ts;
    }

}
