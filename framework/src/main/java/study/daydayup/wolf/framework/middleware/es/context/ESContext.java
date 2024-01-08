package study.daydayup.wolf.framework.middleware.es.context;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.io.sql.SqlExecutor;
import study.daydayup.wolf.common.lang.contract.Context;
import study.daydayup.wolf.framework.middleware.es.config.ESConfig;
import study.daydayup.wolf.framework.middleware.es.search.dsl.executor.ESCountExecutor;
import study.daydayup.wolf.framework.middleware.es.search.dsl.executor.ESSearchExecutor;

/**
 * study.daydayup.wolf.framework.middleware.es.context
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
