package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.result.ProduceResultConverter;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceResult;
import study.daydayup.wolf.framework.middleware.mq.core.producer.ProduceCallback;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.producer
 *
 * @author Wingle
 * @since 2021/12/16 下午12:49
 **/
public class AsyncCallback implements SendCallback {
    private final ProduceCallback callback;

    public AsyncCallback(ProduceCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        ProduceResult produceResult = ProduceResultConverter.from(sendResult);
        callback.onComplete(produceResult, null);
    }

    @Override
    public void onException(Throwable e) {
        callback.onComplete(ProduceResult.FAILURE(), e);
    }
}
