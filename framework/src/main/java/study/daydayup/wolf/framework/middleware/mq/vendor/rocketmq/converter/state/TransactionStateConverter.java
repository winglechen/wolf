package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.state;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.TransactionStateEnum;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.converter.state
 *
 * @author Wingle
 * @since 2021/12/16 上午12:10
 **/
public class TransactionStateConverter implements Converter {
    public static LocalTransactionState toRocket(TransactionStateEnum stateEnum) {
        if (null == stateEnum) {
            return LocalTransactionState.UNKNOW;
        }

        switch (stateEnum) {
            case COMMIT:
                return LocalTransactionState.COMMIT_MESSAGE;
            case ROLLBACK:
                return LocalTransactionState.ROLLBACK_MESSAGE;
            case TIMEOUT:
            case RETRY:
            default:
                return LocalTransactionState.UNKNOW;
        }

    }
}
