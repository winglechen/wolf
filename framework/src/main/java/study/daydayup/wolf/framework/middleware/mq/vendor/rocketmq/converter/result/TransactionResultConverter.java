package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.result;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq.converter.state.TransactionStateConverter;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.TransactionResult;

/**
 * study.daydayup.wolf.framework.middleware.mq.adapter.rocketmq.converter
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
