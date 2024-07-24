package com.wolf.dts.source.offset;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.wolf.common.lang.io.sql.Sql;
import com.wolf.common.lang.io.sql.builder.SqlBuilder;
import com.wolf.common.lang.io.sql.statement.SqlStatement;
import com.wolf.common.lang.exception.lang.IllegalArgumentException;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.common.util.collection.MapUtil;
import com.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wingle
 * @since 2020/2/5 5:26 下午
 **/
@Component
public class MysqlOffsetHolder implements OffsetHolder {
    private static final String HOLDER_TABLE = "offset_holder";
    private static final int MAX_LOCK_TIME = 60 * 5;
    @Resource
    private JdbcTemplate jdbc;
    @Resource
    private MysqlOffsetHolder mysqlOffsetHolder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long get(@NonNull Offset offset) {
        Map<String, Object> row = mysqlOffsetHolder.lockOrInsert(offset);
        if (!isOffsetAvailable(row, offset)) {
            throw new OffsetNotAvailableException(offset);
        }

        mergeRowToOffset(offset, row);
        if (null == row.get("id")) {
            return offset.getOffset();
        }

        int status = mysqlOffsetHolder.updateDbLock(offset);
        if (status <= 0) {
            throw new OffsetLockFailException(offset);
        }

        return offset.getOffset();
    }

    @Override
    public Long read(@NonNull Offset offset) {
        Map<String, Object> row = readRow(offset);
        if (row.isEmpty()) {
            return 0L;
        }

        return Long.valueOf(row.get("offset").toString());
    }

    @Override
    public int set(@NonNull Offset offset) {
        return mysqlOffsetHolder.updateOffsetToDb(offset);
    }

    @Override
    public void lock(Offset offset) {
        Map<String, Object> row = lockOffset(offset);
        if (MapUtil.isEmpty(row)) {
            offset.setOffset(0L);

            String tmpKey = offset.getKey();
            offset.setKey(null);
            insert(offset);
            offset.setKey(tmpKey);

            lockOffset(offset);
            return;
        }

        mergeRowToOffset(offset, row);
    }

    @Override
    public Long increase(Offset offset, long num) {
        offset.setNewOffset(offset.getOffset() + num);
        updateOffset(offset);
        return offset.getOffset();
    }

    @Override
    public Long decrease(Offset offset, long num) {
        if (null == offset.getOffset()) {
            throw new IllegalArgumentException("offset can't be null while decrease");
        }

        offset.setNewOffset(offset.getOffset() - num);
        updateOffset(offset);
        return offset.getOffset();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long lockAndIncrease(@NonNull Offset offset, long num) {
        Map<String, Object> row = lockOffset(offset);
        if (MapUtil.isEmpty(row)) {
            offset.setNewOffset(num);
            offset.setOffset(0L);

            insert(offset);
            lock(offset);
            return offset.getNewOffset();
        }

        mergeRowToOffset(offset, row);
        offset.setNewOffset(offset.getOffset() + num);

        updateOffset(offset);
        return offset.getNewOffset();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long lockAndDecrease(@NonNull Offset offset, long num) {
        if (null != offset.getOffset()) {
            offset.setNewOffset(offset.getOffset() - num);

            insert(offset);
            lock(offset);
            return offset.getNewOffset();
        }

        Map<String, Object> row = lockOffset(offset);
        if (MapUtil.isEmpty(row)) {
            throw new IllegalArgumentException("offset does't exists, and can't be decreased");
        }

        mergeRowToOffset(offset, row);
        offset.setNewOffset(offset.getOffset() - num);

        updateOffset(offset);
        return offset.getNewOffset();
    }

    public int updateOffsetToDb(@NonNull Offset offset) {
        Map<String, Object> data = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();

        if (null != offset.getNewOffset()) {
            data.put("offset", offset.getNewOffset());
        }
        data.put("version", SqlStatement.of(" version + 1 "));
        data.put("updated_at", now);
        data.put("locker", "");
        data.put("lock_at", LocalDateTime.now().minusSeconds(2L * MAX_LOCK_TIME));

        SqlBuilder sql = Sql.update(HOLDER_TABLE)
                .set(data)
                .where(StringUtil.join( "source = '", offset.getSource(), "'"))
                .and(StringUtil.join( "sink = '", offset.getSink(), "'"))
                .and(StringUtil.join( "table_name = '", offset.getTable(), "'"))
                .and(StringUtil.join( "sharding_key = '", offset.getShard(), "'"))
                .and(StringUtil.join( "offset = ", offset.getOffset() , ""))
                .limit(1);
        return jdbc.update(sql.getSql(), sql.getData());
    }

    public int updateDbLock(@NonNull Offset offset) {
        Map<String, Object> data = new HashMap<>();
        data.put("lock_at", LocalDateTime.now());
        data.put("locker", offset.getKey());
        data.put("version", SqlStatement.of(" version + 1 "));

        SqlBuilder sql = Sql.update(HOLDER_TABLE)
                .set(data)
                .where(StringUtil.join( "source = '", offset.getSource(), "'"))
                .and(StringUtil.join( "sink = '", offset.getSink(), "'"))
                .and(StringUtil.join( "table_name = '", offset.getTable(), "'"))
                .and(StringUtil.join( "sharding_key = '", offset.getShard(), "'"))
                .and(StringUtil.join( "version = ", offset.getVersion() , ""))
                .limit(1);
        return jdbc.update(sql.getSql(), sql.getData());
    }

    public int updateOffset(@NonNull Offset offset) {
        Map<String, Object> data = new HashMap<>();
        data.put("offset", offset.getNewOffset());
        data.put("version", SqlStatement.of(" version + 1 "));

        SqlBuilder sql = Sql.update(HOLDER_TABLE)
                .set(data)
                .where(StringUtil.join( "source = '", offset.getSource(), "'"))
                .and(StringUtil.join( "sink = '", offset.getSink(), "'"))
                .and(StringUtil.join( "table_name = '", offset.getTable(), "'"))
                .and(StringUtil.join( "sharding_key = '", offset.getShard(), "'"))
                .and(StringUtil.join( "version = ", offset.getVersion() , ""))
                .limit(1);
        return jdbc.update(sql.getSql(), sql.getData());
    }

    public Map<String, Object> insert(@NonNull Offset offset) {
        Map<String, Object> data = new HashMap<>();
        data.put("source", offset.getSource());
        data.put("sink", offset.getSink());
        data.put("table_name", offset.getTable());
        data.put("sharding_key", offset.getShard());


        if (null != offset.getNewOffset()) {
            data.put("offset", offset.getNewOffset());
        } else {
            data.put("offset", 0L);
        }

        data.put("created_at", LocalDateTime.now());
        data.put("version", 1);
        if (StringUtil.isBlank(offset.getKey())) {
            data.put("locker",offset.getKey());
            data.put("lock_at", LocalDateTime.now());
        }

        SqlBuilder sql = Sql.insert(HOLDER_TABLE)
                .values(data);
        jdbc.update(sql.getSql(), sql.getData());

        return data;
    }

    public Map<String, Object> lockOrInsert(@NonNull Offset offset) {
        Map<String, Object> row = lockOffset(offset);
        if (MapUtil.isEmpty(row)) {
            return mysqlOffsetHolder.insert(offset);
        }

        return row;
    }

    public Map<String, Object> readRow(@NonNull Offset offset) {
        SqlBuilder sql = Sql.select("*")
                .from(HOLDER_TABLE)
                .where(StringUtil.join( "source = '", offset.getSource(), "'"))
                .and(StringUtil.join( "sink = '", offset.getSink(), "'"))
                .and(StringUtil.join( "table_name = '", offset.getTable(), "'"))
                .and(StringUtil.join( "sharding_key = '", offset.getShard(), "'"))
                .limit(1);

        List<Map<String, Object>> rows = jdbc.queryForList(sql.getSql(), sql.getData());
        if (CollectionUtil.isEmpty(rows) || MapUtil.isEmpty(rows.get(0))) {
            return MapUtil.empty();
        }

        return rows.get(0);
    }

    public Map<String, Object> lockOffset(@NonNull Offset offset) {
        SqlBuilder sql = Sql.select("*")
                .from(HOLDER_TABLE)
                .where(StringUtil.join( "source = '", offset.getSource(), "'"))
                .and(StringUtil.join( "sink = '", offset.getSink(), "'"))
                .and(StringUtil.join( "table_name = '", offset.getTable(), "'"))
                .and(StringUtil.join( "sharding_key = '", offset.getShard(), "'"))
                .limit(1)
                .forUpdate();

        List<Map<String, Object>> rows = jdbc.queryForList(sql.getSql(), sql.getData());
        if (CollectionUtil.isEmpty(rows) || MapUtil.isEmpty(rows.get(0))) {
            return MapUtil.empty();
        }

        return rows.get(0);
    }

    private boolean isOffsetAvailable(Map<String, Object> row, Offset offset) {
        if (MapUtil.isEmpty(row)) {
            return false;
        }

        if (StringUtil.isBlank(row.get("locker"))) {
            return true;
        }

        if (offset.getKey().equals(row.get(("locker")))) {
            return true;
        }

        if (null == row.get("lock_at")) {
            return true;
        }

        LocalDateTime lockAt = ( (Timestamp)row.get("lock_at") ).toLocalDateTime();
        LocalDateTime lockTime = LocalDateTime.now().minusSeconds(MAX_LOCK_TIME);
        return lockAt.compareTo(lockTime) <= 0;
    }

    private void mergeRowToOffset(Offset offset, Map<String, Object> row) {
        offset.setVersion(Integer.valueOf(row.get("version").toString()));

        Long lastId = Long.valueOf(row.get("offset").toString());
        offset.setOffset(lastId);
    }

}
