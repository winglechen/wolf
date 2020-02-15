package study.daydayup.wolf.framework.dts.offset;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Long get(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink) {
        String key = formatKey(source, table, shard, sink);
        if (!OffsetLocker.lock(key)) {
            return null;
        }

        Long offset = getOffsetFromDb(source, table, shard, sink);

        OffsetLocker.unlock(key);
        return offset;
    }

    @Override
    public void set(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink, @NonNull Long id) {
        String key = formatKey(source, table, shard, sink);
        if (!OffsetLocker.lock(key)) {
            return;
        }

        updateOffsetToDb(source, table, shard, sink, id);

        OffsetLocker.unlock(key);
    }

    private void updateOffsetToDb(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink, @NonNull Long id) {
        Map<String, Object> data = new HashMap<>();
        data.put("offset", id);
        data.put("version", " version + 1 ");
        data.put("updated_at", DateUtil.asString(LocalDateTime.now()));

        String sql = Sql.update(HOLDER_TABLE)
                .set(data)
                .where(StringUtil.join( "source = '", source, "'"))
                .and(StringUtil.join( "sink = '", sink, "'"))
                .and(StringUtil.join( "table_name = '", table, "'"))
                .and(StringUtil.join( "sharding_key = '", shard, "'"))
                .limit(1)
                .toString();
        jdbc.update(sql);
    }

    private void createOffset(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink) {
        Map<String, Object> data = new HashMap<>();
        data.put("source", source);
        data.put("sink", sink);
        data.put("table_name", table);
        data.put("sharding_key", shard);
        data.put("offset", 0);
        data.put("created_at", DateUtil.asString(LocalDateTime.now()));

        String sql = Sql.insert(HOLDER_TABLE)
                .values(data)
                .toString();
        jdbc.update(sql);
    }

    private Long getOffsetFromDb(@NonNull String source, @NonNull String table, @NonNull String shard, @NonNull String sink) {
        String sql = Sql.select("offset")
                .from(HOLDER_TABLE)
                .where(StringUtil.join( "source = '", source, "'"))
                .and(StringUtil.join( "sink = '", sink, "'"))
                .and(StringUtil.join( "table_name = '", table, "'"))
                .and(StringUtil.join( "sharding_key = '", shard, "'"))
                .limit(1)
                .toString();

        List<Long> offsetList = jdbc.queryForList(sql, Long.class);
        if (!CollectionUtil.hasValue(offsetList)) {
            createOffset(source, table, shard, sink);
            return 0L;
        }

        return offsetList.get(0);
    }

}
