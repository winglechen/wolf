package study.daydayup.wolf.framework.middleware.hbase;

import lombok.Getter;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.framework.middleware.hbase.dao.HBaseDAO;

/**
 * PaymentHBaseDAO
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/8/10 15:31
 **/
@Component
public class PaymentHBaseDAO extends HBaseDAO<PaymentHBase> {

    @Getter
    private final Class<PaymentHBase> tableClass = PaymentHBase.class;
}
