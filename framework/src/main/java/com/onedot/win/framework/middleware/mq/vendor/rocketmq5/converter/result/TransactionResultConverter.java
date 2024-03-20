package com.onedot.win.framework.middleware.mq.vendor.rocketmq5.converter.result;

import org.apache.rocketmq.client.apis.producer.TransactionResolution;
import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.middleware.mq.core.transaction.TransactionResult;
import com.onedot.win.framework.middleware.mq.vendor.rocketmq5.converter.state.TransactionStateConverter;

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
