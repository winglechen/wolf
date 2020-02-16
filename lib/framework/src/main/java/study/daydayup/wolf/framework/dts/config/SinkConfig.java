package study.daydayup.wolf.framework.dts.config;

import lombok.Data;

import java.util.Set;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:06 下午
 **/
@Data
public class SinkConfig {
    private String sinkName;
    private String tableName;
    private Set<String> keyColumns;
}
