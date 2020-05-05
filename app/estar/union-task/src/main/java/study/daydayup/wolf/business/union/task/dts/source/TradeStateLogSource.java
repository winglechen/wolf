package study.daydayup.wolf.business.union.task.dts.source;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.union.task.config.ShardingConfig;
import study.daydayup.wolf.dts.config.SourceConfig;
import study.daydayup.wolf.dts.source.MysqlSource;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.task.dts.source
 *
 * @author Wingle
 * @since 2020/2/8 8:27 下午
 **/
@Component
public class TradeStateLogSource {
    @Resource
    private ShardingConfig shardingConfig;
    @Resource
    private MysqlSource mysqlSource;

    public MysqlSource latest() {
        SourceConfig sourceConfig = SourceConfig.builder()
                .sourceName("latest-trade-log")
                .tableName("trade_state_log")
                .columns("id, buyer_id, seller_id, trade_type, source_state, target_state, amount, currency, payment_method, consign_method, source, tags, created_at")
                .shardingKey(shardingConfig.getShard())
                .build();
        mysqlSource.init(sourceConfig);

        return mysqlSource;
    }
}
