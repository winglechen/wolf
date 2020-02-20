package study.daydayup.wolf.dts.source;

import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.dts.config.SourceConfig;

/**
 * study.daydayup.wolf.dts.source
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

    protected boolean isInit = false;

    @Override
    public Source init(SourceConfig config) {
        if (isInit) {
            return this;
        }
        isInit = true;

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

        return this;
    }

}
