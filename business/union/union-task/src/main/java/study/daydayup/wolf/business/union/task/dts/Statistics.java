package study.daydayup.wolf.business.union.task.dts;

import lombok.Data;

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

    private Map<String, Object> key;
    private Map<String, Object> value;
}
