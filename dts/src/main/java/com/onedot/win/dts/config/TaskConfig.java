package com.onedot.win.dts.config;

import lombok.Data;
import com.onedot.win.dts.sink.Sink;
import com.onedot.win.dts.source.Source;

import java.util.List;

/**
 * com.onedot.win.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:07 下午
 **/
@Data
public class TaskConfig {
    private Source source;
    private List<Sink> sinks;
}
