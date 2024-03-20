package com.onedot.win.common.io.sql.builder;

import lombok.Getter;
import lombok.NonNull;
import com.onedot.win.common.io.sql.statement.Statement;
import com.onedot.win.common.io.sql.util.ValueFormatter;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * com.onedot.win.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 4:25 下午
 **/
public class WhereBuilder {
    private String where;
    private Object[] ps;
    private Map<String, Object> data;
    private boolean prepared;
    private boolean isFirstWhere;

    private boolean filterNullClause = true;
    private boolean filterBlankClause = false;

    @Getter
    private String whereValues;
    @Getter
    private final List<Object> preparedDataList;

    public static WhereBuilder getInstance(String where, Object[] ps, boolean isFirstWhere) {
        return new WhereBuilder(where, ps, isFirstWhere);
    }

    public static WhereBuilder getInstance(@NonNull Map<String, Object> ps, boolean prepared, boolean isFirstWhere) {
        return new WhereBuilder(ps, prepared, isFirstWhere);
    }

    public WhereBuilder(@NonNull String where, Object[] ps, boolean isFirstWhere) {
        this.where = where;
        this.ps = ps;
        this.isFirstWhere = isFirstWhere;

        this.whereValues = "";
        this.preparedDataList = new ArrayList<>();
    }

    public WhereBuilder(@NonNull Map<String, Object> data, boolean prepared, boolean isFirstWhere) {
        this.data = data;
        this.prepared = prepared;
        this.isFirstWhere = isFirstWhere;

        this.whereValues = "";
        this.preparedDataList = new ArrayList<>();
    }


    public void filterNullClause(boolean flag) {
        filterNullClause = flag;
    }

    public void filterBlankClause(boolean flag) {
        filterBlankClause = flag;
    }

    public void parseString() {
        StringBuilder sql = new StringBuilder();
        addWherePrefix(sql);
        sql.append(where);
        whereValues = sql.toString();

        if (ps.length > 0) {
            preparedDataList.addAll(Arrays.asList(ps));
        }
    }

    public void parseMap() {
        if (MapUtil.isEmpty(data)) {
            return;
        }

        StringBuilder sql = new StringBuilder();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value = entry.getValue();
            if (filterNullClause && null == value) {
                continue;
            }

            if (filterBlankClause && StringUtil.isBlank(value)) {
                continue;
            }

            addWherePrefix(sql);
            String column = StringUtil.quote(entry.getKey());
            sql.append(column).append(SqlKeyword.BLANK).append(SqlKeyword.EQUAL).append(SqlKeyword.BLANK);

            if (value instanceof Statement) {
                setValueStatement(sql, (Statement) value);
            } else if (prepared) {
                sql.append(SqlKeyword.QUESTION_MARK).append(SqlKeyword.BLANK);
                preparedDataList.add(value);
            } else {
                sql.append(ValueFormatter.format(value));
            }
        }

        whereValues = sql.toString();
    }

    private void addWherePrefix(StringBuilder sql) {
        if (isFirstWhere) {
            sql.append(SqlKeyword.WHERE);
        } else {
            sql.append(SqlKeyword.AND);
        }
        isFirstWhere = false;
    }

    private void setValueStatement(StringBuilder sql, Statement s) {
        sql.append(s.getSql());
        if (prepared && null != s.getValue()) {
            preparedDataList.add(s.getValue());
        }
    }

}
