package study.daydayup.wolf.business.union.task.service.impl;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.dts.sink.DailyTradeSink;
import study.daydayup.wolf.business.union.task.dts.source.ContractSource;
import study.daydayup.wolf.business.union.task.dts.source.OrderSource;
import study.daydayup.wolf.business.union.task.dts.source.TradeStateLogSource;
import study.daydayup.wolf.business.union.task.dts.transformation.ContractTransformation;
import study.daydayup.wolf.business.union.task.dts.transformation.OrderTransformation;
import study.daydayup.wolf.business.union.task.dts.transformation.TradeStateLogTransformation;
import study.daydayup.wolf.business.union.task.service.DailyTradeService;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.dts.sink.MysqlSink;
import study.daydayup.wolf.dts.source.MysqlSource;
import study.daydayup.wolf.dts.transformation.Statistics;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.service.impl
 *
 * @author Wingle
 * @since 2020/5/5 4:14 下午
 **/
@Component
public class DailyTradeServiceImpl implements DailyTradeService {
    @Resource
    private DailyTradeSink tradeSink;

    @Resource
    private ContractSource contractSource;
    @Resource
    private OrderSource orderSource;
    @Resource
    private TradeStateLogSource stateLogSource;

    @Resource
    private ContractTransformation contractTransformation;
    @Resource
    private OrderTransformation orderTransformation;
    @Resource
    private TradeStateLogTransformation stateLogTransformation;

    @Override
    public void countNewContract() {
        String taskName = "new-contract";
        MysqlSource source = contractSource.latest();
        Table stream = source.getStream(taskName);

        MysqlSink sink = tradeSink.create(taskName, source);
        Statistics statistics = contractTransformation.newCount(stream, sink);
        sink.save(statistics);

    }

    @Override
    public void countTradeStateChange() {
        String taskName = "trade-state-change";
        MysqlSource source = stateLogSource.latest();
        Table stream = source.getStream(taskName);

        MysqlSink sink = tradeSink.create(taskName, source);
        Statistics statistics = stateLogTransformation.latest(stream, sink);
        sink.save(statistics);
    }

    @Override
    public void countNewOrder() {
        String taskName = "new-order";
        MysqlSource source = orderSource.latest();
        Table stream = source.getStream(taskName);

        MysqlSink sink = tradeSink.create(taskName, source);
        Statistics statistics = orderTransformation.newOrder(stream, sink);
        sink.save(statistics);
    }

}
