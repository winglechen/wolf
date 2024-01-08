package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.result;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.ConsumeResult;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.state.ConsumeStateConverter;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.converter
 *
 * @author Wingle
 * @since 2021/12/16 下午10:17
 **/
public class ConsumeResultConverter {

    public static ConsumeConcurrentlyStatus toRocketStatus(ConsumeResult result) {
        if (null == result || null == result.getState()) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeStateConverter.toRocket(result.getState());
    }

    public static ConsumeOrderlyStatus toRocketOrderlyStatus(ConsumeResult result) {
        if (null == result || null == result.getState()) {
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }

        return ConsumeStateConverter.toRocketOrderlyStatus(result.getState());
    }
}