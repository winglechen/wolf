package com.wolf.framework.middleware.es.search.dsl;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.io.sql.SqlResult;
import com.wolf.common.lang.ds.ObjectMap;
import com.wolf.common.lang.exception.lang.IllegalAccessException;
import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.middleware.es.search.AbstractSearchBuilder;
import com.wolf.framework.middleware.es.search.dsl.executor.ESSearchExecutor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ESSearchBuilder extends AbstractSearchBuilder {
    @Getter
    @Setter
    private ESSearchExecutor executor;
    @Getter
    private String indexName;
    private Long from;
    private Integer size;
    private List<String> source;
    @Getter
    private Boolean withRealCount;
    private List<ObjectMap> sort;
    @Getter
    private final ESWhereBuilder esWhereBuilder;
    private boolean filterBlankClause = false;
    private boolean filterNullClause = false;

    public ESSearchBuilder() {
        withRealCount = false;
        esWhereBuilder = new ESWhereBuilder();
    }

    public ESSearchBuilder filterBlankClause(boolean flag) {
        filterBlankClause = flag;
        return this;
    }

    public ESSearchBuilder filterNullClause(boolean flag) {
        filterNullClause = flag;
        return this;
    }

    public ESSearchBuilder from(@NonNull String indexName) {
        this.indexName = indexName;

        return this;
    }

    public ESSearchBuilder select(@NonNull String columns) {
        if (StringUtil.isBlank(columns)) {
            return this;
        }

        source = Arrays.stream(columns.split(","))
                .map(s -> StringUtil.trim(s))
                .collect(Collectors.toList());

        return this;
    }

    public ESSearchBuilder limit(@NonNull Long offset, @NonNull Integer size) {
        if (offset > 10000) {
            throw new IllegalArgumentException("ESSearch offset cannot be greater than 10000");
        }

        this.from = offset;
        this.size = size;

        return this;
    }

    public ESSearchBuilder orderBy(String column, OrderEnum orderEnum) {
        initSortIfNull();

        addSort(column, orderEnum);
        return this;
    }

    public ESSearchBuilder where(@NonNull String column, @NonNull String operator, Object value) {
        esWhereBuilder.where(column, operator, value, filterNullClause, filterBlankClause);
        return this;
    }

    public ESSearchBuilder withRealCount() {
        withRealCount = true;
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

        if (from != null) {
            dsl.put("from", from);
        }
        if (size != null) {
            dsl.put("size", size);
        }
        if (source != null) {
            dsl.put("_source", source);
        }

        if (sort != null) {
            dsl.put("sort", sort);
        }

        ObjectMap query = esWhereBuilder.toDsl();

        if (query != null) {
            dsl.put("query", query);
        }

        return dsl;
    }


    private ESSearchBuilder addSort(String column, OrderEnum orderEnum) {
        initSortIfNull();

        ObjectMap m = new ObjectMap();
        m.put(column, orderEnum.getName());

        sort.add(m);

        return this;
    }

    private void initSortIfNull() {
        if (sort != null) {
            return;
        }

        sort = new LinkedList<>();
    }
}
