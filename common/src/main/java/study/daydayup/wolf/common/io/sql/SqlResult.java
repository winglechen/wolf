package study.daydayup.wolf.common.io.sql;

import lombok.Getter;
import lombok.Setter;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.sql.builder.SqlKeyword;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2021/9/22 下午10:09
 **/
public class SqlResult implements Serializable {
    @Getter
    private List<Map<String, Object>> data;
    @Setter
    private Integer affectedRows;
    @Getter
    @Setter
    private Long insertId;
    @Getter
    @Setter
    private String cursor;

    @Setter
    private Long count;
    @Getter
    @Setter
    private boolean realCountFlag;

    public SqlResult() {
        data = new ArrayList<>();
        affectedRows = 0;
        insertId = null;
        cursor = null;
        realCountFlag = true;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public void addData(Map<String, Object> row) {
        data.add(row);
    }

    /**
     * @return nullable
     */
    public Row getRow() {
        if (CollectionUtil.isEmpty(data)) {
            return null;
        }

        return Row.of(data.get(0));
    }

    public <T> T getObject(Class<T> clazz) {
        Row row = getRow();
        if (row == null) {
            return null;
        }

        return row.toJavaObject(clazz);
    }

    public <T> List<T> getObjectList(Class<T> clazz) {
        Table table = getTable();
        if (table.isEmpty()) {
            return ListUtil.empty();
        }

        List<T> result = new ArrayList<>();
        for (Row row : table) {
            result.add(row.toJavaObject(clazz));
        }

        return result;
    }

    public List<Row> getRows() {
        return data.stream().map(line -> Row.of(line)).collect(Collectors.toList());
    }

    public Table getTable() {
        Table table = new Table();
        if (CollectionUtil.isEmpty(data)) {
            return table;
        }

        for (Map<String, Object> row : data) {
            table.addRow(Row.of(row));
        }
        return table;
    }

    public Long getCount() {
        if (count != null) {
            return count;
        }

        Row row = getRow();
        if (row == null) {
            return 0L;
        }

        Long sqlCount = row.getLong(SqlKeyword.COUNT_KEY);
        if (null != sqlCount) {
            count = sqlCount;
            return count;
        }

        return (long) data.size();
    }

    public int getAffectedRows() {
        return affectedRows;
    }

}
