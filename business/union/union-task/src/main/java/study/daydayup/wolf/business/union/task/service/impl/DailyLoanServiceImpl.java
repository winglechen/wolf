package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.framework.dts.transformation.Operator;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.framework.dts.transformation.Statistics;
import study.daydayup.wolf.business.union.task.service.DailyLoanService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.dts.config.SinkConfig;
import study.daydayup.wolf.framework.dts.config.SourceConfig;
import study.daydayup.wolf.framework.dts.sink.MysqlSink;
import study.daydayup.wolf.framework.dts.source.MysqlSource;
import study.daydayup.wolf.framework.dts.transformation.DbTransformation;

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
        if (CollectionUtil.isEmpty(stream)) {
            return;
        }

        SinkConfig sinkConfig = SinkConfig.builder()
                .sinkName(sinkName)
                .tableName("daily_loan")
                .source(mysqlSource)
                .build()
                .setKeyColumns("org_id", "date")
                ;
        mysqlSink.init(sinkConfig);

        DbTransformation transformation = DbTransformation.newTask(mysqlSink);
        Operator operator;

        operator = transformation.addJob();
        operator.map()
                .rename("seller_id", "org_id")
                .toLocalDate("created_at", "date")
                .toTag();
        operator.match()
                .equal("trade_type", TradeTypeEnum.LOAN_CONTRACT.getCode());
        operator.aggregate()
                .count("request_count");

        operator = transformation.addJob();
        operator.match()
                .equal("trade_type", TradeTypeEnum.LOAN_CONTRACT.getCode())
                .hasTag(TradeTag.FIRST_TRADE);
        operator.aggregate()
                .count("first_request_count");

        Statistics statistics = transformation.transform(stream);
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
