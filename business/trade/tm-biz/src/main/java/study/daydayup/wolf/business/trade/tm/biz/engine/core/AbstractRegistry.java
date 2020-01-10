package study.daydayup.wolf.business.trade.tm.biz.engine.core;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.tm.biz.engine.FilterRegistry;
import study.daydayup.wolf.business.trade.tm.biz.engine.QueryEngine;
import study.daydayup.wolf.business.trade.tm.biz.engine.QueryFilter;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 3:10 下午
 **/
public abstract class AbstractRegistry implements FilterRegistry {
    protected QueryEngine engine;

    public void init(@NonNull QueryEngine engine) {
        this.engine = engine;
    }

    public AbstractRegistry addFilter(QueryFilter... filters) {
        if (filters == null || 0 == filters.length) {
            return this;
        }

        for (QueryFilter filter: filters) {
            engine.addFilter(filter);
        }

        return this;
    }
}


