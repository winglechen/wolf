package study.daydayup.wolf.framework.dts.source.offset;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.util.collection.MapUtil;

import javax.annotation.Resource;
import java.util.Map;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:32 下午
 **/
@Component
public class Offset implements OffsetHolder {
    @Resource
    private MysqlOffsetHolder mysqlOffsetHolder;

    private String source;
    private String table;
    private String shard;

    private boolean isInit = false;

    public Offset init(@NonNull String source, @NonNull String table, @NonNull String shard) {
        this.source = source;
        this.table = table;
        this.shard = shard;

        isInit = true;
        return this;
    }

    public Offset load() {
        if (!isInit) {
            return this;
        }

        Map<String, Long> data = mysqlOffsetHolder.getAll(source, table, shard);
        if (MapUtil.isEmpty(data)) {
            return this;
        }

        MemoryOffsetHolder.getInstance().setAll(source, table, shard, data);
        return this;
    }

    public Offset load(@NonNull String source, @NonNull String table, @NonNull String shard) {
        init(source, table, shard);
        return load();
    }

    public Long get(@NonNull String sink) {
        return get(source, table, shard, sink);
    }

    public void set(@NonNull String sink, @NonNull Long preOffset, @NonNull Long newOffset) {
        set(source, table, shard, sink, preOffset, newOffset);
    }

    @Override
    public Long get(String source, String table, String shard, String sink) {
        Long id = MemoryOffsetHolder.getInstance().get(source, table, shard, sink);
        if (id != null) {
            return id;
        }

        return mysqlOffsetHolder.get(source, table, shard, sink);
    }

    @Override
    public int set(String source, String table, String shard, String sink, Long prefOffset, Long newOffset) {
        int count = mysqlOffsetHolder.set(source, table, shard, sink, prefOffset, newOffset);
        if (count <= 0) {
            return count;
        }

        MemoryOffsetHolder.getInstance().set(source, table, shard, sink, prefOffset, newOffset);
        return count;
    }

}
