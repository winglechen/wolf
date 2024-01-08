package study.daydayup.wolf.dts.config;

import lombok.Data;
import study.daydayup.wolf.dts.sink.Sink;
import study.daydayup.wolf.dts.source.Source;

import java.util.List;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:07 下午
 **/
@Data
public class TaskConfig {
    private Source source;
    private List<Sink> sinks;
}
