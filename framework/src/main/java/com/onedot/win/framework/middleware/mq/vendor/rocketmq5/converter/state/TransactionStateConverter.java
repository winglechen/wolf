package com.onedot.win.framework.middleware.mq.vendor.rocketmq5.converter.state;

import org.apache.rocketmq.client.apis.producer.TransactionResolution;
import com.onedot.win.common.lang.contract.worker.Converter;
import com.onedot.win.framework.middleware.mq.core.transaction.TransactionStateEnum;

/**
 * @author weixing
 * @since 2023/6/9 16:19
 */
public class TransactionStateConverter implements Converter {
    public static TransactionResolution toRocket(TransactionStateEnum stateEnum) {
        if (null == stateEnum) {
            return TransactionResolution.UNKNOWN;
        }

        switch (stateEnum) {
            case COMMIT:
                return TransactionResolution.COMMIT;
            case ROLLBACK:
                return TransactionResolution.ROLLBACK;
            case TIMEOUT:
            case RETRY:
            default:
                return TransactionResolution.UNKNOWN;
        }

    }
}
