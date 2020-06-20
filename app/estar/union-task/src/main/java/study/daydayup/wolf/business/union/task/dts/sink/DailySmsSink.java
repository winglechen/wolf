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
 * @since 2020/6/20 5:33 下午
 **/
@Component
public class DailySmsSink {
    @Resource
    private MysqlSink mysqlSink;

    public MysqlSink create(String taskName, MysqlSource mysqlSource) {
        SinkConfig sinkConfig = SinkConfig.builder()
                .sinkName(taskName)
                .tableName("daily_sms")
                .source(mysqlSource)
                .build()
                .setKeyColumns("org_id", "date", "sms_type");
        mysqlSink.init(sinkConfig);

        return mysqlSink;
    }
}
