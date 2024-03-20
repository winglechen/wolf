package com.onedot.win.framework.middleware.es.search.dsl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import com.onedot.win.common.io.sql.SqlResult;
import com.onedot.win.common.lang.ds.ObjectMap;
import com.onedot.win.common.lang.exception.lang.IllegalAccessException;
import com.onedot.win.framework.middleware.es.search.AbstractSearchBuilder;
import com.onedot.win.framework.middleware.es.search.dsl.executor.ESCountExecutor;

public class ESCountBuilder extends AbstractSearchBuilder {
    @Getter
    @Setter
    private ESCountExecutor executor;
    @Getter
    private String indexName;
    @Getter
    @Setter
    private ESWhereBuilder esWhereBuilder;

    public ESCountBuilder() {
        esWhereBuilder = new ESWhereBuilder();
    }

    public ESCountBuilder from(@NonNull String indexName) {
        this.indexName = indexName;

        return this;
    }

    public ESCountBuilder where(@NonNull String column, @NonNull String operator, Object value) {
        esWhereBuilder.where(column, operator, value);

        return this;
    }

    public SqlResult execute() {
        return execute(false);
    }

    public SqlResult execute(Boolean debug) {
        if (executor == null) {
            throw new IllegalAccessException("ESSearchExecutor has not been set");
        }

        if (null != debug) {
            return executor.execute(this, debug);
        }

        return executor.execute(this);
    }

    public ObjectMap toDsl() {
        ObjectMap dsl = new ObjectMap();
        ObjectMap query = esWhereBuilder.toDsl();
        if (query != null) {
            dsl.put("query", query);
        }

        return dsl;
    }
}
