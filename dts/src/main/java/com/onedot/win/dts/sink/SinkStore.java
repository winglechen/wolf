package com.onedot.win.dts.sink;

/**
 * com.onedot.win.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:21 上午
 **/
public interface SinkStore {
    String DEFAULT_SINK_NAME = "root";

    void save(Sink config);
}
