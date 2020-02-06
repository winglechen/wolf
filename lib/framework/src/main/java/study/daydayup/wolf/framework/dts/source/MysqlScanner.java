package study.daydayup.wolf.framework.dts.source;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.sql.Sql;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/4 5:31 下午
 **/
@Component("mysql")
public class MysqlScanner implements DbScanner, Source {
    private static final int MAX_ROW_NUM = 200;
    private static final int SELECT_LIMIT = 30;

    @Resource
    private JdbcTemplate jdbc;

    @Override
    public Table scan(@NonNull String table, @NonNull Long id) {
        return scan(table, id, Sql.DEFAULT_COLUMNS);
    }

    /**
     *
     * @param table tableName
     * @param id id
     * @param columns columns | default "*"
     * @return Table
     */
    @Override
    public Table scan(@NonNull String table, @NonNull Long id, @NonNull String columns) {
        return null;
    }


}
