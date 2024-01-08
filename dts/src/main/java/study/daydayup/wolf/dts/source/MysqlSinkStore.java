package study.daydayup.wolf.dts.source;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.dts.source.offset.Offset;
import study.daydayup.wolf.dts.source.offset.OffsetGateway;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * study.daydayup.wolf.dts.source
 *
 * @author Wingle
 * @since 2020/2/16 6:12 下午
 **/
@Component
public class MysqlSinkStore implements SinkStore {
    @Resource
    private OffsetGateway offsetGateway;
    @Resource
    private MysqlScanner mysqlScanner;
    @Resource
    private JdbcTemplate jdbc;

    @Override
    public Table getStream(Source source) {
        Offset offset = source.getOffset();
        offset.mergeSourceConfig(source);

        Long lastId = offsetGateway.get(offset);
        if (lastId == null) {
            return null;
        }

        return mysqlScanner.scan(source.getTable(), lastId, source.getColumns());
    }

    @Override
    public Table select(String sql) {
        List<Map<String, Object>> data = select(sql, null);
        return Table.of(data);
    }

    @Override
    public Table select(SqlBuilder sql) {
        List<Map<String, Object>> data = select(sql.getSql(), sql.getData());
        return Table.of(data);
    }

    private List<Map<String, Object>> select(String sql, Object[] args) {
        if (ArrayUtil.isEmpty(args)) {
            return jdbc.queryForList(sql);
        }

        return jdbc.queryForList(sql, args);
    }

}
