package com.wolf.framework.middleware.hbase.executor;

import lombok.extern.slf4j.Slf4j;
import com.wolf.framework.middleware.hbase.OptionBuilder;
import com.wolf.framework.middleware.hbase.option.*;
import com.wolf.framework.middleware.hbase.result.HBaseResult;

/**
 * AbstractExecutor
 *
 * @author rocky
 * @since 2023/8/8 15:09
 **/
@Slf4j
public abstract class AbstractExecutor implements Executor {
    @Override
    public HBaseResult execute(OptionBuilder optionBuilder) {
        try {
            if (optionBuilder.getOptionCriteria() instanceof GetOptionCriteria getOption) {
                return doGet(optionBuilder, getOption);
            } else if (optionBuilder.getOptionCriteria() instanceof GetsOptionCriteria getsOption) {
                return doGets(optionBuilder, getsOption);
            } else if (optionBuilder.getOptionCriteria() instanceof PutOptionCriteria putOption) {
                return doPut(optionBuilder, putOption);
            } else if (optionBuilder.getOptionCriteria() instanceof PutsOptionCriteria putsOption) {
                return doPuts(optionBuilder, putsOption);
            } else if (optionBuilder.getOptionCriteria() instanceof ScanOptionCriteria scanOptionCriteria) {
                return doScan(optionBuilder, scanOptionCriteria);
            } else {
                return HBaseResult.fail("option not support");
            }
        } catch (Exception e) {
            log.error("hbase client query failed", e);
            return HBaseResult.fail(e.getMessage());
        }
    }
}
