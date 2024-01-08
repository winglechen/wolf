package study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.converter.result;

import org.apache.rocketmq.client.apis.producer.TransactionResolution;
import study.daydayup.wolf.common.lang.contract.worker.Converter;
import study.daydayup.wolf.framework.middleware.mq.core.transaction.TransactionResult;
import study.daydayup.wolf.framework.middleware.mq.vendor.rocketmq5.converter.state.TransactionStateConverter;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
public class TransactionResultConverter implements Converter {

    public static TransactionResolution toTransactionState(TransactionResult result) {
        if (result == null) {
            return TransactionResolution.UNKNOWN;
        }

        return TransactionStateConverter.toRocket(result.getState());
    }
}
