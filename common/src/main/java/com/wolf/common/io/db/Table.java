package com.wolf.common.io.db;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.io.enums.UnionEnum;
import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.common.util.collection.ListUtil;
import com.wolf.common.util.collection.MapUtil;
import com.wolf.common.util.lang.BeanUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * com.daydayup.learn.hello.common.lang.ds
 *
 * @author Wingle
 * @since 2019/7/12 10:30 AM
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class Table extends LinkedList<Row> implements Cloneable {
    private static final Table EMPTY_INSTANCE = new Table();
    public static final String DEFAULT_TAG_COLUMN = "tags";
    public static final String DEFAULT_ID_COLUMN = "id";
    public static final String ALL_COLUMNS = "*";
    public static final String DEFAULT_COUNT_COLUMN = "*";

    public static Table of(List<Map<String, Object>> table) {
        return new Table(table);
    }

    public static Table empty() {
        return EMPTY_INSTANCE;
    }

    public Table() {
        super();
    }

    public Table(List<Map<String, Object>> table) {
        union(table);
    }

    public void join(List<Map<String, Object>> data, String byColumn) {
    }

    public void union(List<Map<String, Object>> tableList) {
        union(tableList, UnionEnum.BOTTOM);
    }

    public void union(Table table) {
        union(new ArrayList<>(table), UnionEnum.BOTTOM);
    }

    public void union(List<Map<String, Object>> table, UnionEnum direction) {
        for (Map<String, Object> item : table) {
            switch (direction) {
                case TOP:
                    addFirst(Row.of(item));
                    break;
                case BOTTOM:
                    addLast(Row.of(item));
                    break;
                default:
            }
        }
    }

    public void addRow(Row row) {
        addLast(row);
    }

    public void addRow(Map<String, Object> r) {
        Row row = Row.of(r);
        addRow(row);
    }

    public int sumInteger(String column) {
        int result = 0;
        if (isEmpty()) {
            return result;
        }

        for (Row row : this) {
            Integer value = row.getInteger(column);
            if (value == null) {
                continue;
            }

            result = result + value;
        }

        return result;
    }

    public BigDecimal sumBigDecimal(String column) {
        BigDecimal result = BigDecimal.ZERO;
        if (isEmpty()) {
            return result;
        }

        for (Row row : this) {
            BigDecimal value = row.getBigDecimal(column);
            if (value == null) {
                continue;
            }

            result = result.add(value);
        }

        return result;
    }

    public Row getRow(String column, Object value) {
        if (isEmpty()) {
            return null;
        }

        for (Row row : this) {
            Integer v = row.getInteger(column);
            if (BeanUtil.equals(v, value)) {
                return row;
            }
        }

        return null;
    }

    public Map<Long, Row> getIdMap() {
        if (isEmpty()) {
            return MapUtil.empty();
        }

        Map<Long, Row> map = new HashMap<>();
        for (Row row : this) {
            if (null == row.getLong("id")) {
                throw new IllegalArgumentException("row don't have column id of this table");
            }

            map.put(row.getLong("id"), row);

        }

        return map;
    }

    public Map<Object, Table> group(String column) {
        Map<Object, Table> result = new LinkedHashMap<>();
        if (isEmpty()) {
            return result;
        }

        for (Row row : this) {
            Object key = row.get(column);
            if (key == null) {
                continue;
            }
            Table tmp = result.get(key);
            if (tmp == null) {
                tmp = new Table();
                result.put(key, tmp);
            }

            tmp.addRow(row);
        }

        return result;
    }

    public Long getMaxId() {
        if (isEmpty()) {
            return null;
        }

        Long maxId = null;
        for (Row row : this) {
            if (null == maxId) {
                maxId = row.getId();
                continue;
            }

            maxId = Math.max(maxId, row.getId());
        }

        return maxId;
    }

    public Long getFirstId() {
        if (null == getFirst()) {
            return null;
        }

        return getFirst().getId();
    }

    public Long getLastId() {
        if (null == getLast()) {
            return null;
        }

        return getLast().getId();
    }

    public Long getMaxId(OrderEnum order) {
        if (isEmpty()) {
            return null;
        }

        if (OrderEnum.ASC.equals(order)) {
            return getLast().getId();
        }

        return getFirst().getId();
    }

    public <T> List<T> getColumnList(String column, Class<T> type) {
        if (isEmpty()) {
            return ListUtil.empty();
        }

        List<T> data = new ArrayList<>();
        for (Row row : this) {
            data.add(row.getObject(column, type));
        }

        return data;
    }

    @Override
    @SuppressWarnings("all")
    public Table clone() {
        Table newTable = new Table();

        for (Row row : this) {
            Row newRow = new Row();
            newRow.putAll(row);
            newTable.addRow(newRow);
        }

        return newTable;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

