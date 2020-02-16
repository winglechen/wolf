package study.daydayup.wolf.framework.dts.source;

import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.framework.dts.config.SourceConfig;

/**
 * study.daydayup.wolf.framework.dts.source
 *
 * @author Wingle
 * @since 2020/2/16 6:18 下午
 **/
public abstract class AbstractSource implements Source {
    protected String sourceName;

    protected String tableName;
    protected String shardingKey;

    protected String columns;
    protected OrderEnum order;

    public void init(SourceConfig config) {
        sourceName = config.getSourceName();
        tableName = config.getTableName();

        shardingKey = null != config.getShardingKey()
                ? config.getShardingKey()
                : Source.DEFAULT_SHARDING_KEY;

        columns = null != config.getColumns()
                ? config.getColumns()
                : Table.ALL_COLUMNS;

        order = null != config.getOrder()
                ? config.getOrder()
                : Source.DEFAULT_ORDER;

    }
}
