package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.dts.transformation.LoanTransformation;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.dts.transformation.Statistics;
import study.daydayup.wolf.business.union.task.service.DailyLoanService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.dts.config.SinkConfig;
import study.daydayup.wolf.dts.config.SourceConfig;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.source.MysqlSource;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/2/10 9:42 上午
 **/
@Component
public class DailyLoanServiceImpl implements DailyLoanService {
    @Resource
    private ShardingConfig shardingConfig;
    @Resource
    private MysqlSource mysqlSource;
    @Resource
    private MysqlSink mysqlSink;

    @Resource
    private LoanTransformation dailyLoanTransformation;

    @Override
    public void countLoanContract() {
        String sinkName = "requestCount";

        SourceConfig sourceConfig = SourceConfig.builder()
                .sourceName("latestContract")
                .tableName("contract")
                .columns("id, buyer_id, seller_id, trade_type, tags, created_at")
                .shardingKey(shardingConfig.getShard())
                .build();
        mysqlSource.init(sourceConfig);
        Table stream = mysqlSource.getStream(sinkName);

        SinkConfig sinkConfig = SinkConfig.builder()
                .sinkName(sinkName)
                .tableName("daily_loan")
                .source(mysqlSource)
                .build()
                .setKeyColumns("org_id", "date");
        mysqlSink.init(sinkConfig);

        Statistics statistics = dailyLoanTransformation.latest(stream, mysqlSink);
        mysqlSink.save(statistics);

//        mysqlSource.saveOffset(statistics.getMaxId());
    }

    @Override
    public void countLoanContractState() {

    }

    @Override
    public void countLoanOrder() {

    }

    @Override
    public void countRepayContract() {

    }

    @Override
    public void countRepayOrder() {

    }
}
