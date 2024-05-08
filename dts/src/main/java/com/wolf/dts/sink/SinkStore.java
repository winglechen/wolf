package com.wolf.dts.sink;

/**
 * com.wolf.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:21 上午
 **/
public interface SinkStore {
    String DEFAULT_SINK_NAME = "root";

    void save(Sink config);
}
