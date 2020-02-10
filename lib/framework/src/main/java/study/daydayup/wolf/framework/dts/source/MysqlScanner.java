package study.daydayup.wolf.framework.dts.source;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/4 5:31 下午
 **/
@Component
public class MysqlScanner implements DbScanner, Source {
    private static final int MAX_ROW_NUM = 200;
    private static final int SELECT_LIMIT = 40;

    private static final String ID = "id";
    private static final String CLAUSE = ">";
    private static final OrderEnum DEFAULT_ORDER = OrderEnum.ASC;


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
        Table result = new Table();

        int count = 0, size = 0;
        Long lastId = id;
        List<Map<String, Object>> data;

        while (count < MAX_ROW_NUM ) {
            data = select(table, columns, lastId);
            if (!CollectionUtil.hasValue(data)) {
                break;
            }

            result.union(data);
            size = data.size();
            if (size < SELECT_LIMIT) {
                break;
            }

            count += size;
            lastId = getLastId(data);
            if (lastId == null) {
                break;
            }
        }

        return result;
    }

    private Long getLastId(List<Map<String, Object>> data) {
        Map<String, Object> last = ListUtil.last(data);
        if (last == null) {
            return null;
        }

        return (Long)last.get("id");
    }


    private List<Map<String, Object>> select(@NonNull String table, @NonNull String columns, @NonNull Long id) {
        String sql = Sql.select(columns)
                .from(table)
                .where(StringUtil.joinWith(StringUtil.BLANK, ID, CLAUSE, id))
                .orderBy(ID, DEFAULT_ORDER)
                .limit(SELECT_LIMIT)
                .toString();

        return jdbc.queryForList(sql);
    }


}
