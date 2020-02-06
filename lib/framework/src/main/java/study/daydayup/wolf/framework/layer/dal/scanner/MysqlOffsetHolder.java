package study.daydayup.wolf.framework.layer.dal.scanner;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:26 下午
 **/
@Component
public class MysqlOffsetHolder implements OffsetHolder {
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
}
