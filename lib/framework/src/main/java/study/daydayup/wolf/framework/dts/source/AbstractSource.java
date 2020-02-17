package study.daydayup.wolf.framework.dts.source;

import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.framework.dts.config.SourceConfig;
import study.daydayup.wolf.framework.dts.sink.Sink;

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

    @Override
    public Long getOffset() {
        return getOffset(Sink.DEFAULT_SINK_NAME);
    }

    @Override
    public Long getOffset(String sinkName) {
        return null;
    }

    @Override
    public void saveOffset(Long offset) {
        saveOffset(Sink.DEFAULT_SINK_NAME, offset);
    }

}
