package study.daydayup.wolf.framework.dts.offset;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.framework.dts.config.SourceConfig;

import javax.annotation.Resource;

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
    private String sink;
    private String table;
    private String shard;

    public Offset init(SourceConfig source) {

        return this;
    }

    public Offset load() {

        return this;
    }

    public void init(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink) {
        this.source = source;
        this.table = table;
        this.shard = shard;
        this.sink = sink;
    }

    public Long get() {
        return get(source, table, shard, sink);
    }

    public void set(Long id) {
        set(source, table, shard, sink, id);
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
    public void set(String source, String table, String shard, String sink, Long id) {
        mysqlOffsetHolder.set(source, table, shard, sink, id);
        MemoryOffsetHolder.getInstance().set(source, table, shard, sink, id);
    }
}
