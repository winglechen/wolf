package com.wolf.dts.sink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.wolf.dts.source.offset.Offset;
import com.wolf.dts.transformer.Statistics;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * com.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:06 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sink {
    private String sinkName;
    private String tableName;
    /**
     * 设置数据聚合字段
     * <p>
     * Transformation将会根据字段的值将结果聚合
     */
    private Set<String> groupFields;

    private Offset offset;
    private Statistics statistics;

    public Sink setTable(String table) {
        tableName = table;
        return this;
    }

    public Sink setGroupFields(String... columns) {
        if (null == columns || 0 == columns.length) {
            return this;
        }

        if (null == groupFields) {
            groupFields = new TreeSet<>();
        }

        groupFields.addAll(Arrays.asList(columns));
        return this;
    }

    public static Sink fromOffset(Offset offset) {
        Sink config = new Sink();
        config.setOffset(offset);
        config.setSinkName(offset.getSink());

        return config;
    }

}
