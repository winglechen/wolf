package com.wolf.framework.middleware.hbase;

import lombok.Getter;
import org.springframework.stereotype.Component;
import com.wolf.framework.middleware.hbase.dao.HBaseDAO;

/**
 * PaymentHBaseDAO
 *
 * @author rocky
 * @since 2023/8/10 15:31
 **/
@Component
public class PaymentHBaseDAO extends HBaseDAO<PaymentHBase> {

    @Getter
    private final Class<PaymentHBase> tableClass = PaymentHBase.class;
}
