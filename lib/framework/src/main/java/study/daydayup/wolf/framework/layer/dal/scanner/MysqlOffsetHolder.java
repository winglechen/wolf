package study.daydayup.wolf.framework.layer.dal.scanner;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.util.StringUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:26 下午
 **/
@Component
public class MysqlOffsetHolder implements OffsetHolder {
    private static final String HOLDER_TABLE = "offset_holder";
    @Resource
    private JdbcTemplate jdbc;

    @Override
    public Long get(@NonNull String task, @NonNull String table, @NonNull String shard) {
        String key = formatKey(task, table, shard);
        if (!OffsetLocker.lock(key)) {
            return null;
        }

        OffsetLocker.unlock(key);
        return null;
    }

    @Override
    public void set(@NonNull String task, @NonNull String table, @NonNull String shard, @NonNull Long id) {
        String key = formatKey(task, table, shard);
        if (!OffsetLocker.lock(key)) {
            return;
        }

        OffsetLocker.unlock(key);
    }

    private Long getOffsetFromDb(@NonNull String task, @NonNull String table, @NonNull String shard) {
        String sql = Sql.select("offset")
                .from(HOLDER_TABLE)
                .where(StringUtil.join( "task_name = ", task))
                .and(StringUtil.join( "table_name = ", table))
                .and(StringUtil.join( "sharding_key = ", shard))
                .limit(1)
                .toString();

        return null;
    }
}
