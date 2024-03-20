package com.onedot.win.framework.middleware.es.context;

import lombok.Builder;
import lombok.Data;
import com.onedot.win.common.io.sql.SqlExecutor;
import com.onedot.win.common.lang.contract.Context;
import com.onedot.win.framework.middleware.es.config.ESConfig;
import com.onedot.win.framework.middleware.es.search.dsl.executor.ESCountExecutor;
import com.onedot.win.framework.middleware.es.search.dsl.executor.ESSearchExecutor;

/**
 * com.onedot.win.framework.middleware.es.context
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
