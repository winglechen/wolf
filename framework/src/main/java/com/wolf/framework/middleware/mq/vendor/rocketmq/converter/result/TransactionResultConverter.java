package com.wolf.framework.middleware.mq.vendor.rocketmq.converter.result;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import com.wolf.common.lang.contract.worker.Converter;
import com.wolf.framework.middleware.mq.vendor.rocketmq.converter.state.TransactionStateConverter;
import com.wolf.framework.middleware.mq.core.transaction.TransactionResult;

/**
 * com.wolf.framework.middleware.mq.adapter.rocketmq.converter
 *
 * @author Wingle
 * @since 2021/12/16 上午12:10
 **/
public class TransactionResultConverter implements Converter {

    public static LocalTransactionState toTransactionState(TransactionResult result) {
        if (result == null) {
            return LocalTransactionState.UNKNOW;
        }

        return TransactionStateConverter.toRocket(result.getState());
    }
}
