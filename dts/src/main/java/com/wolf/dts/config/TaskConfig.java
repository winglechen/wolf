package com.wolf.dts.config;

import lombok.Data;
import com.wolf.dts.sink.Sink;
import com.wolf.dts.source.Source;

import java.util.List;

/**
 * com.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:07 下午
 **/
@Data
public class TaskConfig {
    private Source source;
    private List<Sink> sinks;
}
