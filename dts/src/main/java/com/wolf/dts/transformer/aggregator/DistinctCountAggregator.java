package com.wolf.dts.transformer.aggregator;

import lombok.NonNull;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.wolf.common.io.db.Row;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.common.util.time.DateUtil;
import com.wolf.dts.transformer.Statistics;
//import com.wolf.framework.util.ContextUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * com.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class DistinctCountAggregator extends AbstractAggregator implements Aggregator {
    private StringRedisTemplate redis;
    private String hashKey;
    private Date expiredAt;

    @Override
    public void init(@NonNull Statistics statistics, @NonNull String rowColumn, @NonNull String statisticsColumn) {
        super.init(statistics, rowColumn, statisticsColumn);

//        redis = ContextUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);
        initHashKey();
    }

    @Override
    public void aggregate(Row row) {
        String value = row.get(rowColumn).toString();
        Integer count = (Integer) statistics.getCurrentAggregateRowValue(statisticsColumn);
        if (count == null) {
            count = 0;
        }

        if (!isDistinct(value)) {
            return;
        }

        count++;
        statistics.setCurrentAggregateRowValue(statisticsColumn, count);
    }

    @Override
    public void merge(Row row) {

    }

    @Override
    public void format() {
        Formatter.plus(statistics, statisticsColumn);
    }

    private void initHashKey() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        hashKey = StringUtil.join(
                statisticsColumn,
                DateUtil.asString(tomorrow, "yyyyMMdd")
        );

        LocalTime time = LocalTime.of(0, 30);
        LocalDateTime dateTime = LocalDateTime.of(tomorrow, time);
        expiredAt = DateUtil.asDate(dateTime);
    }

    private boolean isDistinct(String value) {
        HashOperations<String, String, Long> operator = redis.opsForHash();

        boolean exists = operator.hasKey(hashKey, value);
        operator.increment(hashKey, value, 1);

        if (exists) {
            return false;
        }

        setExpireTime();
        return true;
    }

    private void setExpireTime() {
        Long ttl = redis.getExpire(hashKey);
        if (null != ttl && ttl < 0) {
            redis.expireAt(hashKey, expiredAt);
        }
    }
}
