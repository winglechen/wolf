package study.daydayup.wolf.common.io.db;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.common.util.collection.CollectionUtil;

import java.util.*;

/**
 * study.daydayup.wolf.business.union.task.dts
 *
 * @author Wingle
 * @since 2020/2/8 8:48 下午
 **/
@Data
public class Statistics {
    private static final String KEY_DELIMITER = ":";

    private List<String> keyColumns;

    private String currentKey;
    private Row currentRow;
    /**
     * key column map
     * {
     *     columnA : columnA.value
     *     columnB : columnB.value
     * }
     */
    private Map<String, Row> data;

    public Statistics() {
        keyColumns = new ArrayList<>();
        data = new HashMap<>();
    }

    public void addKeyColumn(String... columns) {
        if (columns == null || 0 == columns.length) {
            return;
        }

        keyColumns.addAll(Arrays.asList(columns));
    }

    public String addRow(@NonNull Row row) {
        String key = getKeyFromRow(row);
        if (key == null) {
            return null;
        }

        Row data = getCurrentRow(key);
        setKeyColumns(data, row);

        return key;
    }

    public Collection<Row> getRows() {
        return data.values();
    }

    public void set(@NonNull String key,@NonNull Object value) {
        if (null == currentRow) {
            throw new IllegalArgumentException("Please addRow first!");
        }
        currentRow.put(key, value);
    }

    public Object get(@NonNull String key) {
        if (null == currentRow) {
            throw new IllegalArgumentException("Please addRow first!");
        }

        return currentRow.get(key);
    }

    public String getCurrentKey() {
        return currentKey;
    }

    public boolean setCurrentKey(@NonNull String key) {
        Row row = data.get(key);
        if (row == null) {
            return false;
        }

        initCurrent(key, row);
        return true;
    }

    private String getKeyFromRow(@NonNull Row row) {
        if (!CollectionUtil.hasValue(keyColumns)) {
            return null;
        }

        List<Object> keyList = new ArrayList<>();
        for (String column : keyColumns) {
            Object value = row.get(column);
            if (value == null) {
                return null;
            }
            keyList.add(value);
        }

        return CollectionUtil.join(KEY_DELIMITER, keyList);
    }

    private Row getCurrentRow(@NonNull String key) {
        Row row = data.get(key);
        if (row == null) {
            row = new Row();
            data.put(key, row);
        }

        initCurrent(key, row);
        return row;
    }

    private void initCurrent(String key, Row row) {
        currentKey = key;
        currentRow = row;
    }

    private void setKeyColumns(Row data, Row row) {
        if (!CollectionUtil.hasValue(keyColumns)) {
            return;
        }

        for (String column : keyColumns) {
            data.put(column, row.get(column));
        }
    }

}
