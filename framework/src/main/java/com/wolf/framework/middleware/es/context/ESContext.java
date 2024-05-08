package com.wolf.framework.middleware.es.context;

import lombok.Builder;
import lombok.Data;
import com.wolf.common.io.sql.SqlExecutor;
import com.wolf.common.lang.contract.Context;
import com.wolf.framework.middleware.es.config.ESConfig;
import com.wolf.framework.middleware.es.search.dsl.executor.ESCountExecutor;
import com.wolf.framework.middleware.es.search.dsl.executor.ESSearchExecutor;

/**
 * com.wolf.framework.middleware.es.context
 *
 * @author Wingle
 * @since 2021/12/7 上午1:46
 **/
@Data
@Builder
public class ESContext implements Context {
    private boolean debug = false;
    private ESConfig config;
    private SqlExecutor sqlExecutor;
    private ESSearchExecutor esSearchExecutor;
    private ESCountExecutor esCountExecutor;
}
