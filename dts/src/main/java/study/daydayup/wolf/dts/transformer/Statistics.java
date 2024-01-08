package study.daydayup.wolf.dts.transformer;

import lombok.Data;
import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.business.union.task.dts
 *
 * @author Wingle
 * @since 2020/2/8 8:48 下午
 **/
@Data
public class Statistics {
    private static final String KEY_DELIMITER = ":";

    private Set<String> aggregateKeys;
    private Row currentAggregateRow;


    private Long minOffsetId;
    private Long maxOffsetId;
    /**
     * Aggregate Row Map
     * <p>
     * the key is the value combined by keyColumns of row. <br>
     * the value is the aggregate row which contains the bellow fields:
     * <pre>
     * - [keyColumns[0]]: row[keyColumn[0]]
     * - [keyColumns[1]]: row[keyColumn[1]]
     * - ...
     * - [keyColumns[n]]: row[keyColumn[n]]
     * - [statisticsColumn1]: value of rows calculated by Aggregator
     * - [statisticsColumn2]: value of rows calculated by Aggregator
     * - ...
     * </pre>
     */
    private Map<String, Row> aggregateRowMap;

    public Statistics() {
        aggregateKeys = new TreeSet<>();
        aggregateRowMap = new HashMap<>();
    }

    public void setAggregateKeys(@NonNull Set<String> columns) {
        aggregateKeys = columns;
    }

    public void setCurrentAggregateRow(@NonNull Row row) {
        String key = getKeyFromRow(row);
        if (key == null) {
            return;
        }

        currentAggregateRow = getOrNewAggregateRow(key, row);
    }

    public Collection<Row> getAggregateRows() {
        return aggregateRowMap.values();
    }

    public void setCurrentAggregateRowValue(@NonNull String key, @NonNull Object value) {
        if (null == currentAggregateRow) {
            throw new IllegalArgumentException("Please setCurrentAggregateRow first!");
        }
        currentAggregateRow.put(key, value);
    }

    public Object getCurrentAggregateRowValue(@NonNull String key) {
        if (null == currentAggregateRow) {
            throw new IllegalArgumentException("Please setCurrentAggregateRow first!");
        }

        return currentAggregateRow.get(key);
    }

    public void setMinAndMaxIds(@NonNull Row row) {
        Long id = row.getId();

        setMinOffsetId(id);
        setMaxOffsetId(id);
    }

    private void setMinOffsetId(Long id) {
        if (null == minOffsetId) {
            minOffsetId = id;
        } else {
            minOffsetId = Math.min(id, minOffsetId);
        }
    }

    private void setMaxOffsetId(Long id) {
        if (null == maxOffsetId) {
            maxOffsetId = id;
        } else {
            maxOffsetId = Math.max(id, maxOffsetId);
        }
    }

    private String getKeyFromRow(@NonNull Row row) {
        if (CollectionUtil.isEmpty(aggregateKeys)) {
            return null;
        }

        List<Object> keyList = new ArrayList<>();
        for (String column : aggregateKeys) {
            Object value = row.get(column);
            if (value == null) {
                throw new IllegalArgumentException("Key Column can't be null");
            }
            keyList.add(value);
        }

        return CollectionUtil.join(KEY_DELIMITER, keyList);
    }

    private Row getOrNewAggregateRow(@NonNull String key, Row row) {
        Row aggregateRow = aggregateRowMap.get(key);
        if (aggregateRow == null) {
            aggregateRow = new Row();
            setKeyColumnsForAggregateRow(aggregateRow, row);
            aggregateRowMap.put(key, aggregateRow);
        }

        return aggregateRow;
    }

    private void setKeyColumnsForAggregateRow(Row aggregateRow, Row row) {
        if (!CollectionUtil.notEmpty(aggregateKeys)) {
            return;
        }

        for (String column : aggregateKeys) {
            aggregateRow.put(column, row.get(column));
        }
    }

}
