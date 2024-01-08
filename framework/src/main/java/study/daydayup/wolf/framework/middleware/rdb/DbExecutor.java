package study.daydayup.wolf.framework.middleware.rdb;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import study.daydayup.wolf.common.io.sql.SqlExecutor;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.middleware.sharding.hint.SqlHint;

import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.framework.middleware.rdb
 *
 * @author Wingle
 * @since 2021/9/22 下午9:40
 **/
@Slf4j
public class DbExecutor implements SqlExecutor {
    private final JdbcTemplate jdbcTemplate;
    private boolean debug = false;

    public DbExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void init() {
        RDB.setExecutor(this);
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public SqlResult execute(SqlBuilder sqlBuilder) {
        return execute(sqlBuilder, debug);
    }

    @Override
    public SqlResult execute(@NonNull SqlBuilder sql, boolean debugInCurrentSession) {
        if (debugInCurrentSession) {
            log.info("sql: {}; data: {};", sql.getSql(), sql.getPreparedDataList());
        }

        SqlHint hint = null;
        if (null != sql.getTableShard() && StringUtil.notBlank(sql.getTable())) {
            hint = SqlHint.getInstance().add(
                    sql.getTable(), sql.getDbShard(), sql.getTableShard()
            );
        }

        try {
            SqlResult result;
            if (sql.isSelect()) {
                result = query(sql);
            } else {
                result = update(sql);
            }

            return result;
        } finally {
            if (hint != null) {
                hint.close();
            }
        }
    }

    public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) throws DataAccessException {
        return jdbcTemplate.batchUpdate(sql, pss);
    }


    public int[] batchUpdate(String sql, String table, Integer dbShard, Integer tableShard, BatchPreparedStatementSetter pss) {
        SqlHint hint = null;
        if (table != null && dbShard != null && tableShard != null) {
            hint = SqlHint.getInstance().add(table, dbShard, tableShard);
        }

        try {
            return jdbcTemplate.batchUpdate(sql, pss);
        } finally {
            if (hint != null) {
                hint.close();
            }
        }
    }


    //TODO ADD update support
    @Override
    public SqlResult execute(String sql) {
        List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
        SqlResult result = new SqlResult();
        result.setData(data);
        return result;
    }

    public void t(){
    }

    private SqlResult query(@NonNull SqlBuilder sql) {
        List<Map<String, Object>> data;
        if (ArrayUtil.notEmpty(sql.getData())) {
            data = jdbcTemplate.queryForList(sql.getSql(), sql.getData());
        } else {
            data = jdbcTemplate.queryForList(sql.getSql());
        }

        SqlResult result = new SqlResult();
        result.setData(data);
        return result;
    }

    private SqlResult update(@NonNull SqlBuilder sql) {
        int count = 0;
        if (ArrayUtil.notEmpty(sql.getData())) {
            count = jdbcTemplate.update(sql.getSql(), sql.getData());
        } else {
            count = jdbcTemplate.update(sql.getSql());
        }
        SqlResult result = new SqlResult();
        result.setAffectedRows(count);
        return result;
    }

}
