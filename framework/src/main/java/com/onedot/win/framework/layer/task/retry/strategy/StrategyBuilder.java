package com.onedot.win.framework.layer.task.retry.strategy;

import lombok.NonNull;
import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.layer.task.retry.RetryStrategyMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * com.onedot.win.framework.layer.task.retry.strategy
 *
 * @author Wingle
 * @since 2022/1/14 下午10:41
 **/
public class StrategyBuilder {
    private String key;
    private final RetryStrategyMap strategyMap;
    private final List<Long> delayArray;
    private Long endlessDelay;
    private TimeUnit unit;

    public StrategyBuilder(RetryStrategyMap strategyMap) {
        this.strategyMap = strategyMap;
        this.delayArray = new ArrayList<>();
    }

    public StrategyBuilder key(String key) {
        if (StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("RetryStrategy key can't be blank");
        }

        this.key = key;
        return this;
    }

    public StrategyBuilder delay(@NonNull Long delay) {
        delayArray.add(delay);

        return this;
    }

    public StrategyBuilder endlessDelay(@NonNull Long delay) {
        endlessDelay = delay;

        return this;
    }

    public StrategyBuilder delay(@NonNull Collection<Long> delays) {
        if (CollectionUtil.notEmpty(delays)) {
            delayArray.addAll(delays);
        }

        return this;
    }

    public StrategyBuilder unit(TimeUnit unit) {
        this.unit = unit;

        return this;
    }

    public void execute() {
        DefaultRetryStrategy strategy = new DefaultRetryStrategy();
        strategy.setKey(key);
        strategy.setDelayArray(delayArray);

        if (null != unit) {
            strategy.setUnit(unit);
        }
        if (null != endlessDelay) {
            strategy.setEndlessDelay(endlessDelay);
        }

        strategyMap.put(key, strategy);
    }
}
