package com.onedot.win.framework.middleware.mq.vendor.rocketmq.converter.state;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.middleware.mq.core.transaction.TransactionStateEnum;

/**
 * com.onedot.win.framework.middleware.mq.adapter.rocketmq.converter.state
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
