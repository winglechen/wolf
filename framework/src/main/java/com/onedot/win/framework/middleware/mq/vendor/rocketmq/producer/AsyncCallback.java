package com.onedot.win.framework.middleware.mq.vendor.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import com.onedot.win.framework.middleware.mq.vendor.rocketmq.converter.result.ProduceResultConverter;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceResult;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceCallback;

/**
 * com.onedot.win.framework.middleware.mq.adapter.rocketmq.producer
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
