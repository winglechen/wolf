package com.onedot.win.framework.middleware.es.bootstrap;

import lombok.NonNull;
import com.onedot.win.framework.middleware.es.ES;
import com.onedot.win.framework.middleware.es.config.ESConfig;
import com.onedot.win.framework.middleware.es.context.ESContext;
import com.onedot.win.framework.middleware.es.search.dsl.executor.ESCountExecutor;
import com.onedot.win.framework.middleware.es.search.dsl.executor.ESSearchExecutor;
import com.onedot.win.framework.middleware.es.search.sql.ESSqlExecutor;

/**
 * com.onedot.win.framework.middleware.es.bootstrap
 *
 * @author Wingle
 * @since 2021/12/7 上午1:45
 **/
public class ESBootstrap {
    public static void bootstrap(@NonNull ESConfig config) {
        ESSqlExecutor esSqlExecutor = new ESSqlExecutor();
        esSqlExecutor.setEsConfig(config);

        ESSearchExecutor esSearchExecutor = new ESSearchExecutor();
        esSearchExecutor.setEsConfig(config);

        ESCountExecutor esCountExecutor = new ESCountExecutor();
        esCountExecutor.setEsConfig(config);

        ESContext context = ESContext.builder()
                .config(config)
                .sqlExecutor(esSqlExecutor)
                .esSearchExecutor(esSearchExecutor)
                .esCountExecutor(esCountExecutor)
                .build();

        ES.bootstrap(context);
    }
}
