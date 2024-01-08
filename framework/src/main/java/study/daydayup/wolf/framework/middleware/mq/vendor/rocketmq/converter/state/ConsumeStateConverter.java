package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.state;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.core.consumer.ConsumeStateEnum;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.converter.state
 *
 * @author Wingle
 * @since 2021/12/16 下午10:19
 **/
public class ConsumeStateConverter implements Converter {

    public static ConsumeConcurrentlyStatus toRocket(ConsumeStateEnum stateEnum) {
        if (stateEnum == null) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        if (stateEnum == ConsumeStateEnum.SUCCESS) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }

    public static ConsumeOrderlyStatus toRocketOrderlyStatus(ConsumeStateEnum stateEnum) {
        if (stateEnum == null) {
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }

        if (stateEnum == ConsumeStateEnum.SUCCESS) {
            return ConsumeOrderlyStatus.SUCCESS;
        }

        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
    }
}
