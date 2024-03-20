package com.onedot.win.framework.middleware.mq.vendor.mock.producer;

import lombok.extern.slf4j.Slf4j;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.exception.InvalidParameterException;
import com.onedot.win.framework.middleware.mq.config.MQProducerConfig;
import com.onedot.win.framework.middleware.mq.core.MQVendor;
import com.onedot.win.framework.middleware.mq.core.message.MessageSendTypeEnum;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceCallback;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceRequest;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceResult;
import com.onedot.win.framework.middleware.mq.core.producer.ProduceStateEnum;
import com.onedot.win.framework.middleware.mq.core.producer.VendorProducer;
import com.onedot.win.framework.middleware.mq.core.producer.exception.ProducerStartFailedException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
@Slf4j
public class MockProducer implements VendorProducer {
    //@Getter
    private final MQProducerConfig config;

    private static final AtomicInteger PRODUCER_ID_SEQ = new AtomicInteger(1);

    private String instanceName;

    public MockProducer(MQProducerConfig config) {
        this.config = config;
    }

    @Override
    public String getVendorType() {
        return MQVendor.VENDOR_ROCKET_MQ;
    }

    @Override
    public String getVendorInstanceName() {
        return instanceName;
    }

    @Override
    public void start() {
        try {
            this.instanceName = StringUtil.joinWith(
                "-", "producer",
                config.getVendorConfig().getVendorId(), PRODUCER_ID_SEQ.getAndIncrement()
            );
        } catch (Exception e) {
            log.error("[MQ] mockmq producer start failed: ", e);
            throw new ProducerStartFailedException("mockmq producer start failed");
        }
    }

    @Override
    public void shutdown() {
        log.info("[MQ] mockmq producer is going to close. InstanceName={}", getVendorInstanceName());
    }

    @Override
    public ProduceResult send(ProduceRequest produceRequest) {
        MessageSendTypeEnum sendType = produceRequest.getSendType();
        if (null == sendType) {
            return syncSend(produceRequest);
        }

        switch (sendType) {
            case ONE_WAY:
                return oneWaySend(produceRequest);
            case SCHEDULE:
                return scheduledSend(produceRequest);
            case ORDER:
                return orderedSend(produceRequest);
            default:
                return syncSend(produceRequest);
        }
    }

    @Override
    public void send(ProduceRequest produceRequest, ProduceCallback callback) {
        asyncSend(produceRequest, callback);
    }

    public ProduceResult syncSend(ProduceRequest produceRequest) {
        return ProduceResult.builder()
            .state(ProduceStateEnum.SUCCESS)
            .messageId(StringUtil.uuid())
            .build();
    }

    public void asyncSend(ProduceRequest produceRequest, ProduceCallback callback) {
        final CompletableFuture<Object> future = CompletableFuture.completedFuture(new Object());

        future.whenCompleteAsync((object, t) -> {
            if (null != t) {
                log.error("[MQ] mockmq async send message failed. cause: {}", t.getMessage(), t);
                callback.onComplete(ProduceResult.FAILURE(), t);
                return;
            }

            String messageId = StringUtil.uuid();
            log.info("[MQ] mockmq async send message successfully, messageId={}", messageId);

            ProduceResult produceResult = ProduceResult.SUCCESS();
            produceResult.setMessageId(messageId);
            callback.onComplete(produceResult, null);

        });
    }

    public ProduceResult oneWaySend(ProduceRequest produceRequest) {
        return ProduceResult.SUCCESS();
    }

    public ProduceResult orderedSend(ProduceRequest produceRequest) {
        String messageGroup = String.valueOf(produceRequest.getOrderBy());
        if (null == messageGroup) {
            return send(produceRequest);
        }

        return ProduceResult.builder()
            .state(ProduceStateEnum.SUCCESS)
            .messageId(StringUtil.uuid())
            .build();
    }

    public ProduceResult scheduledSend(ProduceRequest produceRequest) {
        if (null == produceRequest.getActiveAt() && null == produceRequest.getDelay()) {
            throw new InvalidParameterException("activeAt and delay should be both not null.");
        }

        return ProduceResult.builder()
            .state(ProduceStateEnum.SUCCESS)
            .messageId(StringUtil.uuid())
            .build();
    }
}
