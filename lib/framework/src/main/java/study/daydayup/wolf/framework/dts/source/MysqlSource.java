package study.daydayup.wolf.framework.dts.source;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.config.SourceConfig;
import study.daydayup.wolf.framework.dts.offset.Offset;
import study.daydayup.wolf.framework.dts.sink.Sink;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.dts.source
 *
 * @author Wingle
 * @since 2020/2/16 6:12 下午
 **/
@Component
public class MysqlSource extends AbstractSource implements Source {
    @Resource
    private Offset offset;
    @Resource
    private MysqlScanner mysqlScanner;

    @Override
    public Source init(SourceConfig config) {
        super.init(config);
        offset.load(sourceName, tableName, shardingKey);

        return this;
    }

    @Override
    public Table getStream() {
        Long lastId = offset.get(Sink.DEFAULT_SINK_NAME);
        if (lastId == null) {
            return null;
        }

        return mysqlScanner.scan(tableName, lastId, columns);
    }

    @Override
    public Long getOffset(@NonNull String sinkName) {
        return offset.get(sourceName, tableName, shardingKey, sinkName);
    }

    @Override
    public void saveOffset(@NonNull String sinkName, @NonNull Long newOffset) {
        offset.set(sourceName, tableName, shardingKey, sinkName, newOffset);
    }
}
