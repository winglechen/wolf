package study.daydayup.wolf.framework.dts.offset;

import lombok.NonNull;
import org.springframework.stereotype.Component;

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

    private String task;
    private String table;
    private String shard;

    public void init(@NonNull String task, @NonNull String table, String shard) {
        this.task = task;
        this.table = table;
        this.shard = shard;
    }

    public Long get() {
        return get(task, table, shard);
    }

    public void set(Long id) {
        set(task, table, shard, id);
    }

    @Override
    public Long get(String task, String table, String shard) {
        Long id = MemoryOffsetHolder.getInstance().get(task, table, shard);
        if (id != null) {
            return id;
        }

        return mysqlOffsetHolder.get(task, table, shard);
    }

    @Override
    public void set(String task, String table, String shard, Long id) {
        mysqlOffsetHolder.set(task, table, shard, id);
        MemoryOffsetHolder.getInstance().set(task, table, shard, id);
    }
}
