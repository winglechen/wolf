package com.wolf.framework.middleware.es.search.sql;

import lombok.Getter;
import lombok.NonNull;
import com.wolf.common.io.sql.builder.SqlBuilder;
import com.wolf.common.io.sql.builder.SqlKeyword;
import com.wolf.common.util.lang.StringUtil;

import java.util.ArrayList;

public class ESSqlBuilder extends SqlBuilder {
    /**
     * specify the es server name
     */
    @Getter
    private String server;
    private String format;

    public ESSqlBuilder count() {
        return select(SqlKeyword.DEFAULT_COUNT);
    }

    public ESSqlBuilder select() {
        return select(SqlKeyword.DEFAULT_COLUMNS, false);
    }

    public ESSqlBuilder select(boolean withCount) {
        return select(SqlKeyword.DEFAULT_COLUMNS, withCount);
    }

    public ESSqlBuilder select(@NonNull String columns) {
        return select(columns, false);
    }

    public ESSqlBuilder select(@NonNull String columns, boolean withCount) {
        this.withCount = withCount;
        this.prepared = false;
        this.preparedDataList = new ArrayList<>();

        sql = new StringBuilder();
        sql.append(SqlKeyword.SELECT).append(columns).append(SqlKeyword.BLANK);

        return this;
    }

    @Override
    public SqlBuilder limit(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;

        return this;
    }

    public ESSqlBuilder server(String server) {
        this.server = server;

        return this;
    }

    public ESSqlBuilder format(String format) {
        this.format = format;

        return this;
    }

    public String getFormat() {
        if (StringUtil.isBlank(format)) {
            format = "json";
        }

        return format;
    }

    @Override
    public String getSql() {
        return sql.toString().replace("`", "");
    }

}
