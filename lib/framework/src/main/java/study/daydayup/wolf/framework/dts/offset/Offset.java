package study.daydayup.wolf.framework.dts.offset;

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
