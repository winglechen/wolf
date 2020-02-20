package study.daydayup.wolf.dts.sink;

import study.daydayup.wolf.dts.transformation.Statistics;
import study.daydayup.wolf.dts.config.SinkConfig;

import java.util.Set;

/**
 * study.daydayup.wolf.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:21 上午
 **/
public interface Sink {
    String DEFAULT_SINK_NAME = "root";

    Sink init(SinkConfig config);
    Set<String> getKeyColumns();
    boolean isDuplicated(Long id);
    Long getOffset();
    void save(Statistics statistics);
}
