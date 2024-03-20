package com.onedot.win.common.io.sql.builder;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import com.onedot.win.common.io.enums.CrudEnum;
import com.onedot.win.common.io.enums.OrderEnum;
import com.onedot.win.common.io.sql.SqlExecutor;
import com.onedot.win.common.io.sql.SqlResult;
import com.onedot.win.common.io.sql.clause.ClauseGateway;
import com.onedot.win.common.io.sql.statement.Statement;
import com.onedot.win.common.lang.exception.lang.IllegalAccessException;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.onedot.win.common.io.sql
 *
 * @author Wingle
 * @since 2020/9/22 2:46 下午
 **/
public class SqlBuilder {
    protected StringBuilder sql;
    @Getter
    @Setter
    protected SqlExecutor executor;
    @Getter
    protected boolean prepared;
    @Getter
    protected final CrudEnum crud;
    @Getter
    protected List<Object> preparedDataList;
    @Getter
    protected String table;
    @Getter
    protected Integer dbShard;
    @Getter
    protected Integer tableShard;

    /**
     * store limit for paging
     */
    @Getter
    protected Integer limit;
    @Getter
    protected Integer offset;

    /**
     * count switch
     * true: SqlResult will contain count attribute
     */
    @Getter
    protected boolean withCount = false;

    /**
     * store columnWithSelect string
     * to be replaced by select count(*)
     * when withCount is true
     */
    protected String columns;

    /**
     * store order by string
     * to be replaced by empty string
     * when withCount is true
     * ( maybe not required )
     */
    protected String orderBy;


    protected boolean filterNullClause = false;
    protected boolean filterBlankClause = false;

    protected boolean isFirstWhere = true;
    protected boolean isFirstOrder = true;
    protected boolean isFirstGroup = true;
    protected boolean isFirstValue = true;
    protected boolean isFirstSet = true;
    protected boolean isOrMode = false;

    public SqlBuilder() {
        this(StringUtil.EMPTY, true);
    }

    public SqlBuilder(boolean prepared) {
        this(StringUtil.EMPTY, prepared);
    }

    public SqlBuilder(String query) {
        this(query, true);
    }

    public SqlBuilder(String query, boolean prepared) {
        this(query, prepared, null);
    }

    public SqlBuilder(String query, boolean prepared, List<Object> dataList) {
        this.sql = new StringBuilder(query);
        this.prepared = prepared;

        if (query.startsWith(SqlKeyword.SELECT)) {
            crud = CrudEnum.SELECT;
        } else if (query.startsWith(SqlKeyword.INSERT)) {
            crud = CrudEnum.INSERT;
        } else if (query.startsWith(SqlKeyword.UPDATE)) {
            crud = CrudEnum.UPDATE;
        } else if (query.startsWith(SqlKeyword.DELETE)) {
            crud = CrudEnum.DELETE;
        } else {
            crud = null;
        }

        if (null == dataList) {
            this.preparedDataList = new ArrayList<>();
        } else {
            this.preparedDataList = dataList;
        }
    }

    public boolean isSelect() {
        return CrudEnum.SELECT.equals(crud);
    }

    public boolean isInsert() {
        return CrudEnum.INSERT.equals(crud);
    }

    public boolean isUpdate() {
        return CrudEnum.SELECT.equals(crud)
                || CrudEnum.UPDATE.equals(crud)
                || CrudEnum.DELETE.equals(crud)
                ;
    }

    public Object[] getData() {
        return preparedDataList.toArray();
    }

    public String getSql() {
        return sql.toString();
    }

    public SqlBuilder shard(String table, Integer dbShard, Integer tableShard) {
        this.table = table;
        this.dbShard = dbShard;
        this.tableShard = tableShard;
        return this;
    }

    public SqlBuilder shard(Integer dbShard, Integer tableShard) {
        this.dbShard = dbShard;
        this.tableShard = tableShard;
        return this;
    }

    public SqlBuilder union(@NonNull String columns) {
        return UnionBuilder.union(this, columns);
    }

    public SqlBuilder unionAll(@NonNull String columns) {
        return UnionBuilder.unionAll(this, columns);
    }

    public SqlBuilder from(@NonNull String table) {
        return from(table, null);
    }

    public SqlBuilder from(@NonNull String table, String alias) {
        this.table = table;
        sql.append(FromBuilder.from(table, alias));
        return this;
    }

    public SqlBuilder forceIndex(@NonNull String index) {
        sql.append("force index (" + index + ")");
        return this;
    }

    public SqlBuilder withCount(boolean flag) {
        withCount = flag;
        return this;
    }

    public SqlBuilder filterNullClause(boolean flag) {
        filterNullClause = flag;
        return this;
    }

    public SqlBuilder filterBlankClause(boolean flag) {
        filterBlankClause = flag;
        return this;
    }

    public SqlBuilder leftJoin(@NonNull String table, @NonNull String on) {
        return leftJoin(table, null, on);
    }

    public SqlBuilder leftJoin(@NonNull String table, String alias, String on) {
        sql.append(SqlKeyword.LEFT_JOIN).append(JoinBuilder.join(table, alias, on));
        return this;
    }

    public SqlBuilder rightJoin(@NonNull String table, String on) {
        return rightJoin(table, null, on);
    }

    public SqlBuilder rightJoin(@NonNull String table, String alias, String on) {
        sql.append(SqlKeyword.RIGHT_JOIN).append(JoinBuilder.join(table, alias, on));
        return this;
    }

    public SqlBuilder innerJoin(@NonNull String table, String on) {
        return innerJoin(table, null, on);
    }

    public SqlBuilder innerJoin(@NonNull String table, String alias, String on) {
        sql.append(SqlKeyword.INNER_JOIN).append(JoinBuilder.join(table, alias, on));
        return this;
    }

    public SqlBuilder outerJoin(@NonNull String table, String on) {
        return outerJoin(table, null, on);
    }

    public SqlBuilder outerJoin(@NonNull String table, String alias, String on) {
        sql.append(SqlKeyword.OUTER_JOIN).append(JoinBuilder.join(table, alias, on));
        return this;
    }

    public SqlBuilder fullOuterJoin(@NonNull String table, String on) {
        return fullOuterJoin(table, null, on);
    }

    public SqlBuilder fullOuterJoin(@NonNull String table, String alias, String on) {
        sql.append(SqlKeyword.FULL_OUTER_JOIN).append(JoinBuilder.join(table, alias, on));
        return this;
    }

    public SqlBuilder or() {
        closeOr();
        isOrMode = true;
        sql.append(SqlKeyword.BLANK).append(SqlKeyword.LEFT_BRACKET).append(SqlKeyword.BLANK);

        return this;
    }

    public SqlBuilder having(@NonNull String where, Object... ps) {
        sql.append(SqlKeyword.HAVING).append(where);
        if (prepared && ps.length > 0) {
            preparedDataList.addAll(Arrays.asList(ps));
        }

        return this;
    }

    public SqlBuilder and(@NonNull String where, Object... ps) {
        return where(where, ps);
    }

    public SqlBuilder and(@NonNull String where) {
        return where(where);
    }

    public SqlBuilder and(@NonNull String column, String clause, Object value) {
        return where(column, clause, value);
    }

    public SqlBuilder where(@NonNull Map<String, Object> ps) {
        WhereBuilder builder = WhereBuilder.getInstance(ps, prepared, isFirstWhere);
        builder.filterNullClause(filterNullClause);
        builder.filterBlankClause(filterBlankClause);

        builder.parseMap();
        isFirstWhere = false;

        sql.append(builder.getWhereValues());
        preparedDataList.addAll(builder.getPreparedDataList());
        return this;
    }

    public SqlBuilder where(@NonNull String where, Object... ps) {
        WhereBuilder builder = WhereBuilder.getInstance(where, ps, isFirstWhere);

        builder.parseString();
        isFirstWhere = false;

        sql.append(builder.getWhereValues());
        if (CollectionUtil.notEmpty(builder.getPreparedDataList())) {
            preparedDataList.addAll(builder.getPreparedDataList());
        }

        return this;
    }

    public SqlBuilder where(@NonNull String column, String clause, Object value) {
        if (filterNullClause && null == value) {
            return this;
        }

        if (filterBlankClause) {
            if (value instanceof String && StringUtil.isBlank(value)) {
                return this;
            }
        }

        ClauseGateway clauseGateway = new ClauseGateway();
        clauseGateway.setPrepared(prepared);
        Statement statement = clauseGateway.build(column, clause, value);

        addWherePrefix();
        sql.append(statement.getSql());
        if (CollectionUtil.notEmpty(statement.getValues())) {
            preparedDataList.addAll(statement.getValues());
        }

        return this;
    }

    public SqlBuilder orderBy(@NonNull String column) {
        return orderBy(column, OrderEnum.DESC);
    }

    public SqlBuilder orderBy(@NonNull String column, OrderEnum order) {
        sql.append(OrderBuilder.orderBy(column, order, isFirstOrder));
        isFirstOrder = false;
        return this;
    }

    public SqlBuilder groupBy(@NonNull String column) {
        sql.append(GroupBuilder.groupBy(column, isFirstGroup));
        isFirstGroup = false;
        return this;
    }

    public SqlBuilder groupBy(@NonNull List<String> columns) {
        sql.append(GroupBuilder.groupBy(columns, isFirstGroup));
        isFirstGroup = false;
        return this;
    }

    public SqlBuilder limit(int limit) {
        return limit(0, limit);
    }

    public SqlBuilder limit(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;

        sql.append(LimitBuilder.limit(offset, limit));
        return this;
    }

    public SqlBuilder forUpdate() {
        sql.append(" FOR UPDATE ");
        return this;
    }

    public SqlBuilder duplicateUpdate(@NonNull Map<String, Object> data) {
        return set(data, true);
    }


    public SqlBuilder set(@NonNull String column, Object value) {
        Map<String, Object> map = new HashMap<>(2);
        map.put(column, value);

        return set(map);
    }

    public SqlBuilder set(@NonNull Map<String, Object> data) {
        return set(data, false);
    }

    public SqlBuilder set(@NonNull Map<String, Object> data, boolean onDuplicateKey) {
        if (MapUtil.isEmpty(data)) {
            return this;
        }

        UpdateBuilder builder = UpdateBuilder.build(data, isFirstSet, prepared, onDuplicateKey);

        sql.append(builder.getUpdateValues());
        preparedDataList.addAll(builder.getPreparedDataList());

        isFirstSet = false;
        return this;
    }

    public SqlBuilder values(@NonNull Map<String, Object> data) {
        InsertBuilder insertBuilder = InsertBuilder.build(data, prepared, isFirstValue);
        sql.append(insertBuilder.getInsertColumns());
        sql.append(insertBuilder.getInsertValues());
        preparedDataList.addAll(insertBuilder.getPreparedDataList());

        isFirstValue = false;
        return this;
    }

    public SqlResult execute(Boolean debug) {
        if (executor == null) {
            throw new IllegalAccessException("sql Executor has not been set");
        }

        if (debug != null) {
            return executor.execute(this, debug);
        }

        return executor.execute(this);
    }

    public SqlResult execute() {
        return execute(null);
    }

    @Override
    public String toString() {
        closeOr();
        return sql.toString();
    }

    protected void addWherePrefix() {
        if (isFirstWhere) {
            sql.append(SqlKeyword.WHERE);
        } else {
            sql.append(SqlKeyword.AND);
        }
        isFirstWhere = false;
    }

    protected void closeOr() {
        if (!isOrMode) {
            return;
        }

        sql.append(SqlKeyword.RIGHT_BRACKET);
        isOrMode = false;
    }
}
