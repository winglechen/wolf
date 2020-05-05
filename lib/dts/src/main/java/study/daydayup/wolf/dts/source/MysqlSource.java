package study.daydayup.wolf.dts.source;

import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.dts.config.SourceConfig;
import study.daydayup.wolf.dts.source.offset.Offset;
import study.daydayup.wolf.dts.sink.Sink;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.dts.source
 *
 * @author Wingle
 * @since 2020/2/16 6:12 下午
 **/
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MysqlSource extends AbstractSource implements Source {
    @Resource
    private Offset offset;
    @Resource
    private MysqlScanner mysqlScanner;

    private Map<String, Long> offsetMap;

    @Override
    public Source init(SourceConfig config) {
        super.init(config);

        offset.load(sourceName, tableName, shardingKey);
        offsetMap = new HashMap<>();
        return this;
    }

    @Override
    public Table getStream() {
        return getStream(Sink.DEFAULT_SINK_NAME);
    }

    @Override
    public Table getStream(String sinkName) {
        Long lastId = getOffset(sinkName);
        if (lastId == null) {
            return null;
        }

        return mysqlScanner.scan(tableName, lastId, columns);
    }

    @Override
    public Long getOffset() {
        return getOffset(Sink.DEFAULT_SINK_NAME);
    }

    @Override
    public Long getOffset(@NonNull String sinkName) {
        if (null != offsetMap.get(sinkName)) {
            return offsetMap.get(sinkName);
        }

        Long id = offset.get(sourceName, tableName, shardingKey, sinkName);
        offsetMap.put(sinkName, id);

        return id;
    }

    @Override
    public int saveOffset(Long newOffset) {
        return saveOffset(Sink.DEFAULT_SINK_NAME, newOffset);
    }

    @Override
    public int saveOffset(@NonNull String sinkName, @NonNull Long newOffset) {
        Long preOffset = getOffset(sinkName);
        return offset.set(sourceName, tableName, shardingKey, sinkName, preOffset, newOffset);
    }
}
