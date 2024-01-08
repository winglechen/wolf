package study.daydayup.wolf.common.io.jdbc;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.lang.exception.lang.ClassNotFoundException;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JDBC {
    private final static Map<String, JDBC> jdbcMap = new HashMap<>();
    @Getter
    private final JdbcExecutor jdbcExecutor;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            log.error("can't find class: com.mysql.cj.jdbc.Driver; error: ", e);
            throw new ClassNotFoundException("com.mysql.cj.jdbc.Driver");
        }
    }

    public static JDBC connect(String url, String username, String password) {
        String key = formatKey(url, username, password);
        JDBC jdbc = jdbcMap.get(key);
        if (jdbc != null) {
            return jdbc;
        }

        jdbc = new JDBC(url, username, password);
        jdbcMap.put(key, jdbc);

        return jdbc;
    }

    public static void closeAll() {
        for (Map.Entry<String, JDBC> entry : jdbcMap.entrySet()) {
            entry.getValue().getJdbcExecutor().close();
        }
    }

    public void close() {
        jdbcExecutor.close();
    }

    public JDBC(String url, String user, String password) {
        jdbcExecutor = new JdbcExecutor(url, user, password);
    }

    public SqlBuilder select(String columns) {
        SqlBuilder sqlBuilder = Sql.select(columns, false);
        sqlBuilder.setExecutor(jdbcExecutor);
        return sqlBuilder;
    }

    public SqlResult execute(@NonNull String sql) {
        return jdbcExecutor.execute(sql);
    }

    private static String formatKey(String url, String username, String password) {
        return StringUtil.joinWith(":", url, username, password);
    }
}
