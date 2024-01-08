package study.daydayup.wolf.framework.layer.task.retry;

import lombok.NonNull;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.task.retry.strategy.Daily7RetryStrategy;
import study.daydayup.wolf.framework.layer.task.retry.strategy.DefaultRetryStrategy;
import study.daydayup.wolf.framework.layer.task.retry.strategy.RetryStrategy;

import java.util.HashMap;

/**
 * study.daydayup.wolf.framework.layer.task.retry
 *
 * @author Wingle
 * @since 2021/12/13 上午2:22
 **/
public class RetryStrategyMap extends HashMap<String, RetryStrategy> {
    public RetryStrategyMap() {
        add(new DefaultRetryStrategy());
        add(new Daily7RetryStrategy());
    }

    public void add(@NonNull RetryStrategy strategy) {
        put(strategy.getKey(), strategy);
    }

    public RetryStrategy getInstance(String key) {
        if (StringUtil.isBlank(key)) {
            return get(RetryStrategyConst.DEFAULT_RETRY_STRATEGY);
        }
        return get(key);
    }
}
