package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.business.union.task.dts.transformation.UserTransformation;
import study.daydayup.wolf.business.union.task.service.DailyKoiService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.dts.config.SinkConfig;
import study.daydayup.wolf.dts.config.SourceConfig;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.source.MysqlSource;
import study.daydayup.wolf.dts.transformation.Statistics;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/5/5 10:50 上午
 **/
@Component
public class DailyKoiServiceImpl implements DailyKoiService {
    @Resource
    private ShardingConfig shardingConfig;
    @Resource
    private MysqlSource mysqlSource;
    @Resource
    private MysqlSink mysqlSink;
    @Resource
    private UserTransformation userTransformation;

    @Override
    public void countPvAndUv() {

    }

    @Override
    public void countRegister() {
        SourceConfig sourceConfig = SourceConfig.builder()
                .sourceName("onion-latest-user")
                .tableName("user")
                .columns("id, org_id, account_id, created_at")
                .shardingKey(shardingConfig.getShard())
                .build();
        mysqlSource.init(sourceConfig);

        String sinkName = "onion-register-count";
        Table stream = mysqlSource.getStream(sinkName);

        SinkConfig sinkConfig = SinkConfig.builder()
                .sinkName(sinkName)
                .tableName("daily_koi")
                .source(mysqlSource)
                .build()
                .setKeyColumns("org_id", "date");

        Statistics statistics = userTransformation.latest(stream, mysqlSink);
        mysqlSink.save(statistics);
        mysqlSource.saveOffset(statistics.getMaxId());

    }

    @Override
    public void countIndianInfoState() {

    }
}
