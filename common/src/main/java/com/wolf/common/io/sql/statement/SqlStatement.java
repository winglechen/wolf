package com.wolf.common.io.sql.statement;

import com.wolf.common.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * com.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/2/6 11:13 下午
 **/
public class SqlStatement implements Statement {
    private final String sql;
    private final List<Object> valueList;

    public static SqlStatement of(String sql) {
        return new SqlStatement(sql);
    }

    public static SqlStatement of(String sql, Collection<Object> values) {
        return new SqlStatement(sql, values);
    }

    public static SqlStatement of(String sql, Object value) {
        return new SqlStatement(sql, value);
    }

    public SqlStatement(String sql) {
        this(sql, null);
    }

    public SqlStatement(String sql, Object value) {
        this.sql = sql;

        valueList = new ArrayList<>();
        if (null != value) {
            valueList.add(value);
        }
    }

    public SqlStatement(String sql, Collection<Object> values) {
        this.sql = sql;

        valueList = new ArrayList<>();

        if (CollectionUtil.notEmpty(values)) {
            valueList.addAll(values);
        }
    }

    public void addValue(Object value) {
        valueList.add(value);
    }

    @Override
    public String toString() {
        return sql;
    }

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public Object getValue() {
        if (CollectionUtil.isEmpty(valueList)) {
            return null;
        }
        return valueList.get(0);
    }

    @Override
    public List<Object> getValues() {
        return valueList;
    }


}
