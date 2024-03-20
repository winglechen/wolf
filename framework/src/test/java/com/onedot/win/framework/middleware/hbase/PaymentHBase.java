package com.onedot.win.framework.middleware.hbase;

import lombok.Data;
import com.onedot.win.framework.middleware.hbase.table.HBaseTable;
import com.onedot.win.framework.middleware.hbase.executor.hbase.annotation.HBaseFiled;
import com.onedot.win.framework.middleware.hbase.executor.hbase.annotation.HBaseTableName;

/**
 * PaymentHBase
 *
 * @author rocky
 * @since 2023/7/27 14:55
 **/
@Data
@HBaseTableName(source = "HBASE-1", name = "test")
public class PaymentHBase implements HBaseTable {

    @HBaseFiled(family = "cf", qualifier = "a")
    private String paymentNo;
}
