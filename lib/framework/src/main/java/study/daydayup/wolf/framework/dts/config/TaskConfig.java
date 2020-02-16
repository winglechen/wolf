package study.daydayup.wolf.framework.dts.config;

import lombok.Data;

import java.util.List;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:07 下午
 **/
@Data
public class TaskConfig {
    private SourceConfig source;
    private List<SinkConfig> sinks;
}
