package com.wolf.dbclient;

import com.wolf.dbclient.sql.builder.SqlBuilder;
import com.wolf.dbclient.sql.SqlExecutor;
import com.wolf.dbclient.sql.SqlResult;
import com.wolf.common.util.collection.ArrayUtil;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * com.wolf.framework.middleware.rdb
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

        SqlResult result;
        if (sql.isSelect()) {
            result = query(sql);
        } else {
            result = update(sql);
        }

        return result;
    }

    public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) throws DataAccessException {
        return jdbcTemplate.batchUpdate(sql, pss);
    }


    public int[] batchUpdate(String sql, String table, Integer dbShard, Integer tableShard, BatchPreparedStatementSetter pss) {
        return jdbcTemplate.batchUpdate(sql, pss);
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
