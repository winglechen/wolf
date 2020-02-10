package study.daydayup.wolf.common.io.db;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.business.union.task.dts
 *
 * @author Wingle
 * @since 2020/2/8 8:48 下午
 **/
@Data
public class Statistics {
    private String taskName;
    private String tableName;
    private String shardingKey;
    private Long offset;

    private List<String> keyColumns;

    /**
     * key column map
     * {
     *     columnA : columnA.value
     *     columnB : columnB.value
     * }
     */
    private Map<String, Object> key;
    private Map<String, Object> value;
}
