package study.daydayup.wolf.framework.layer.dal.scanner;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/4 5:31 下午
 **/
@Component("mysql")
public class MysqlScanner implements DbScanner {
    private static int DEFAULT_LIMIT = 10;
    private static OrderEnum DEFAULT_ORDER = OrderEnum.ASC;

    @Resource
    private JdbcTemplate jdbc;

    public Table scan(@NonNull String table, @NonNull Long from) {
        return scan(table, from, DEFAULT_LIMIT);
    }

    public Table scan(@NonNull String table, @NonNull Long from, int limit) {
        return scan(table, from, limit, DEFAULT_ORDER);
    }

    public Table scan(@NonNull String table, @NonNull Long from, int limit, OrderEnum order) {
        return null;
    }

}
