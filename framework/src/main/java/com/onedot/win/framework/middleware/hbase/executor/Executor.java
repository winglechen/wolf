package com.onedot.win.framework.middleware.hbase.executor;

import com.onedot.win.framework.middleware.hbase.option.*;
import com.onedot.win.framework.middleware.hbase.result.HBaseResult;
import com.onedot.win.framework.middleware.hbase.OptionBuilder;
import com.onedot.win.framework.middleware.hbase.table.HBaseTable;

/**
 * Excutor
 *
 * @author rocky
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
