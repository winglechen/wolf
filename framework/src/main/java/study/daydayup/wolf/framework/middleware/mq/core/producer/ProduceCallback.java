package study.daydayup.wolf.framework.middleware.mq.core.producer;

/**
 * TODO
 *
 * @author: YIK
 * @since: 2022/2/25 4:13 PM
 */
public interface ProduceCallback {
    void onComplete(ProduceResult produceResult, Throwable e);
}
