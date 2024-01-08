package study.daydayup.wolf.framework.layer.task.retry.strategy;

import study.daydayup.wolf.framework.layer.task.retry.Delay;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.framework.layer.task.retry.strategy
 *
 * @author Wingle
 * @since 2021/12/13 上午2:14
 **/
public interface RetryStrategy {
    String getKey();

    Delay nextTry(int times, LocalDateTime since);

    Delay nextTry(int times);
}
