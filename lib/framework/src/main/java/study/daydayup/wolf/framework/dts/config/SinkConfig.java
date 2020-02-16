package study.daydayup.wolf.framework.dts.config;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.dts.source.Source;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:06 下午
 **/
@Data
@Builder
public class SinkConfig {
    private String sinkName;
    private String tableName;
    private Set<String> keyColumns;

    private Source source;

    public SinkConfig setKeyColumns(String... columns) {
        if (null == columns || 0 == columns.length) {
            return this;
        }

        if (null == keyColumns) {
            keyColumns = new TreeSet<>();
        }

        keyColumns.addAll(Arrays.asList(columns));
        return this;
    }

}
