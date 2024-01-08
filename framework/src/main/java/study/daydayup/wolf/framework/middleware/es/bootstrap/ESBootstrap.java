package study.daydayup.wolf.framework.middleware.es.bootstrap;

import lombok.NonNull;
import study.daydayup.wolf.framework.middleware.es.ES;
import study.daydayup.wolf.framework.middleware.es.config.ESConfig;
import study.daydayup.wolf.framework.middleware.es.context.ESContext;
import study.daydayup.wolf.framework.middleware.es.search.dsl.executor.ESCountExecutor;
import study.daydayup.wolf.framework.middleware.es.search.dsl.executor.ESSearchExecutor;
import study.daydayup.wolf.framework.middleware.es.search.sql.ESSqlExecutor;

/**
 * study.daydayup.wolf.framework.middleware.es.bootstrap
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
