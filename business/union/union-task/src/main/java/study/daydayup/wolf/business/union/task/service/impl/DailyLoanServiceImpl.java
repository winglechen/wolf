package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.framework.dts.config.OffsetConfig;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.business.union.task.dts.sink.DailyLoanSink;
import study.daydayup.wolf.business.union.task.dts.transformation.DailyLoanTransformation;
import study.daydayup.wolf.business.union.task.service.DailyLoanService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.dts.config.SourceConfig;
import study.daydayup.wolf.framework.dts.offset.Offset;
import study.daydayup.wolf.framework.dts.sink.MysqlEditor;
import study.daydayup.wolf.framework.dts.source.MysqlScanner;
import study.daydayup.wolf.framework.dts.source.MysqlSource;

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
    private DailyLoanSink dailyLoanSink;

    @Resource
    private Offset offset;
    @Resource
    private OffsetConfig offsetConfig;
    @Resource
    private ShardingConfig shardingConfig;


    @Resource
    private MysqlScanner mysqlScanner;
    @Resource
    private MysqlSource mysqlSource;
    @Resource
    private MysqlEditor mysqlEditor;


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
        statistics.setTable("daily_loan");
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
