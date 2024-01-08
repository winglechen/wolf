package study.daydayup.wolf.framework.middleware.hbase.executor;

import study.daydayup.wolf.framework.middleware.hbase.option.*;
import study.daydayup.wolf.framework.middleware.hbase.result.HBaseResult;
import study.daydayup.wolf.framework.middleware.hbase.OptionBuilder;
import study.daydayup.wolf.framework.middleware.hbase.table.HBaseTable;

/**
 * Excutor
 *
 * @author rocky <luojing@onion-pay.com>
 * @since 2023/7/26 15:57
 **/
public interface Executor {
    HBaseResult execute(OptionBuilder optionBuilder);

    HBaseResult doGet(OptionBuilder optionBuilder, GetOptionCriteria getOption) throws Exception;

    HBaseResult doGets(OptionBuilder optionBuilder, GetsOptionCriteria getsOption) throws Exception;

    HBaseResult doPut(OptionBuilder optionBuilder, PutOptionCriteria putOption) throws Exception;

    HBaseResult doPuts(OptionBuilder optionBuilder, PutsOptionCriteria putOption) throws Exception;

    HBaseResult doScan(OptionBuilder optionBuilder, ScanOptionCriteria scanOption) throws Exception;

}
