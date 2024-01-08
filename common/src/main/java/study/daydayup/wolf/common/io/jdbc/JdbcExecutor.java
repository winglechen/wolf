package study.daydayup.wolf.common.io.jdbc;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.io.sql.SqlExecutor;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.lang.exception.lang.IllegalArgumentException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class JdbcExecutor implements SqlExecutor {
    private boolean debug = false;
    private final String url;
    private final String username;
    private final String password;
    private Connection conn;

    public JdbcExecutor(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        connect();
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public SqlResult execute(SqlBuilder sqlBuilder) {
        return execute(sqlBuilder, this.debug);
    }

    @Override
    public SqlResult execute(SqlBuilder sqlBuilder, boolean debugInCurrentSession) {
        if (debugInCurrentSession) {
            log.info("JdbcExecutor execute sql: {}", sqlBuilder.getSql());
        }

        if (!sqlBuilder.isSelect()) {
            throw new IllegalArgumentException("JdbcExecutor only support select");
        }

        return executeQuery(sqlBuilder.getSql());
    }

    @Override
    public SqlResult execute(String sql) {
        return executeQuery(sql);
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            log.error("close connection failed: ", e);
        }
    }

    private void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error("fail to connection to database: ", e);
            throw new IllegalArgumentException("invalid connection config");
        }
    }

    private SqlResult executeQuery(String sql) {
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(sql)) {
                return formatQueryResult(resultSet);
            }
        } catch (SQLException e) {
            log.error("fail to executeQuery: sql:{}; error:", sql, e);
            throw new IllegalArgumentException("fail to executeQuery: sql: " + sql);
        }
    }

    private SqlResult formatQueryResult(@NonNull ResultSet resultSet) throws SQLException {
        SqlResult sqlResult = new SqlResult();
        List<String> columnNames = getColumnList(resultSet);

        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (String cn : columnNames) {
                row.put(cn, resultSet.getObject(cn));
            }

            sqlResult.addData(row);
        }

        return sqlResult;
    }

    private List<String> getColumnList(ResultSet resultSet) throws SQLException {
        ResultSetMetaData md = resultSet.getMetaData();
        int columnCount = md.getColumnCount();
        return IntStream.range(0, columnCount)
                .mapToObj(i -> {
                    try {
                        return md.getColumnName(i + 1);
                    } catch (SQLException e) {
                        log.error(e.getMessage(), e);
                        return "?";
                    }
                })
                .collect(Collectors.toList());
    }
}
