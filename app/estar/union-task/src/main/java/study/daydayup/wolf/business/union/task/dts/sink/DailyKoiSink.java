package study.daydayup.wolf.business.union.task.dts.sink;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.dts.config.SinkConfig;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.source.MysqlSource;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.dts.sink
 *
 * @author Wingle
 * @since 2020/2/8 6:05 下午
 **/
@Component
public class DailyKoiSink {
    @Resource
    private MysqlSink mysqlSink;

    public MysqlSink create(String taskName, MysqlSource mysqlSource) {
        SinkConfig sinkConfig = SinkConfig.builder()
                .sinkName(taskName)
                .tableName("daily_koi")
                .source(mysqlSource)
                .build()
                .setKeyColumns("org_id", "date");
        mysqlSink.init(sinkConfig);

        return mysqlSink;
    }
}
