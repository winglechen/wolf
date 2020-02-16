package study.daydayup.wolf.framework.dts.sink;

import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.framework.dts.config.SinkConfig;

/**
 * study.daydayup.wolf.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:21 上午
 **/
public interface Sink {
    String DEFAULT_SINK_NAME = "root";

    Sink init(SinkConfig config);
    void save(Statistics statistics);
}
