package study.daydayup.wolf.framework.middleware.es;

import study.daydayup.wolf.framework.middleware.es.context.ESContext;
import study.daydayup.wolf.framework.middleware.es.search.dsl.ESCountBuilder;
import study.daydayup.wolf.framework.middleware.es.search.dsl.ESSearchBuilder;
import study.daydayup.wolf.framework.middleware.es.search.sql.ESSqlBuilder;

/**
 * study.daydayup.wolf.framework.middleware.es
 *
 * @author Wingle
 * @since 2021/12/5 上午1:45
 **/
public class ES {
    private static ESContext context;

    public static void setDebug(boolean debug) {
        if (context == null) {
            return;
        }

        context.setDebug(debug);
        context.getSqlExecutor().setDebug(debug);
        context.getEsSearchExecutor().setDebug(debug);
        context.getEsCountExecutor().setDebug(debug);
    }

    public static void bootstrap(ESContext esContext) {
        context = esContext;
    }

    public static ESSearchBuilder search() {
        ESSearchBuilder esSearchBuilder = new ESSearchBuilder();
        esSearchBuilder.setExecutor(context.getEsSearchExecutor());

        return esSearchBuilder;
    }

    public static ESCountBuilder count() {
        ESCountBuilder esCountBuilder = new ESCountBuilder();
        esCountBuilder.setExecutor(context.getEsCountExecutor());

        return esCountBuilder;
    }

    public static ESSqlBuilder sql() {
        ESSqlBuilder sqlBuilder = new ESSqlBuilder();
        sqlBuilder.setExecutor(context.getSqlExecutor());

        return sqlBuilder;
    }

}
