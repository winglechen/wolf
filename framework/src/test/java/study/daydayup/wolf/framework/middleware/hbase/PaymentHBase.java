package study.daydayup.wolf.framework.middleware.hbase;

import lombok.Data;
import study.daydayup.wolf.framework.middleware.hbase.table.HBaseTable;
import study.daydayup.wolf.framework.middleware.hbase.executor.hbase.annotation.HBaseFiled;
import study.daydayup.wolf.framework.middleware.hbase.executor.hbase.annotation.HBaseTableName;

/**
 * PaymentHBase
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/27 14:55
 **/
@Data
@HBaseTableName(source = "HBASE-1", name = "test")
public class PaymentHBase implements HBaseTable {

    @HBaseFiled(family = "cf", qualifier = "a")
    private String paymentNo;
}
