package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.business.union.task.dts.transformation.DailyLoanTransformation;
import study.daydayup.wolf.business.union.task.service.DailyLoanService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.dts.config.SinkConfig;
import study.daydayup.wolf.framework.dts.config.SourceConfig;
import study.daydayup.wolf.framework.dts.offset.Offset;
import study.daydayup.wolf.framework.dts.sink.MysqlEditor;
import study.daydayup.wolf.framework.dts.sink.MysqlSink;
import study.daydayup.wolf.framework.dts.source.MysqlScanner;
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
    private DailyLoanTransformation dailyLoanTransformation;

    @Resource
    private Offset offset;
    @Resource
    private ShardingConfig shardingConfig;

    @Resource
    private MysqlScanner mysqlScanner;
    @Resource
    private MysqlSource mysqlSource;
    @Resource
    private MysqlEditor mysqlEditor;
    @Resource
    private MysqlSink mysqlSink;


    @Override
    public void countLoanContract() {
        String source = "latestContract";
        String sink = "newRequest";
        String table = "contract";
        String shard = shardingConfig.getShard();
        offset.init(source, table, shard, sink);

        Long lastId = offset.get();
        if (lastId == null) {
            return;
        }

        Table contracts = mysqlScanner.scan(table, lastId, "id, buyer_id, seller_id, trade_type, tags, created_at");
        if (!CollectionUtil.hasValue(contracts)) {
            return;
        }

        Statistics statistics = dailyLoanTransformation.transform(contracts);
        mysqlEditor.save(offset, statistics);
    }

    @Override
    public void countLoanContractState() {
        SourceConfig sourceConfig = SourceConfig.builder()
                .sourceName("latestContract")
                .tableName("contract")
                .shardingKey(shardingConfig.getShard())
                .build();

        mysqlSource.init(sourceConfig);
        Table stream = mysqlSource.getStream();

        SinkConfig sinkConfig = SinkConfig.builder()
                .sinkName("requestCount")
                .tableName("daily_loan")
                .source(mysqlSource)
                .build()
                .setKeyColumns("org_id", "create_date")
                ;
        mysqlSink.init(sinkConfig);

        DbTransformation transformation = DbTransformation.newTask(mysqlSink);
        Operator operator;

        operator = transformation.addJob();
        operator.map()
                .rename("seller_id", "org_id")
                .toLocalDate("created_at", "create_date")
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

        mysqlSource.saveOffset(statistics.getMaxId());
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
