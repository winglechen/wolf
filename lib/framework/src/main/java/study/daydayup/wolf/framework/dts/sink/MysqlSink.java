package study.daydayup.wolf.framework.dts.sink;

import lombok.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.collection.SetUtil;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * study.daydayup.wolf.framework.dts.sink
 *
 * @author Wingle
 * @since 2020/2/16 6:59 下午
 **/
@Component
public class MysqlSink extends AbstractSink  implements Sink {
    private Statistics statistics;

    @Resource
    private JdbcTemplate jdbc;

    @Override
    public void save(Statistics statistics) {
        if (null == statistics) {
            return;
        }

        if (configInvalid()) {
            return;
        }

        this.statistics = statistics;
        if (hasNoData()) {
            return;
        }
        if (offsetInvalid()) {
            return;
        }

        saveToDb();
    }

    private boolean configInvalid() {
        if (!isInit) {
            return true;
        }

        if (null == config || null == offset) {
            return true;
        }

        if (null == config.getSource()) {
            return true;
        }

        if (null == config.getSinkName()) {
            return true;
        }

        return SetUtil.isEmpty(config.getKeyColumns());
    }

    private boolean hasNoData() {
        Collection<Row> data = statistics.getRows();
        return CollectionUtil.isEmpty(data);
    }

    private boolean offsetInvalid() {
        if (offset == null) {
            return true;
        }

        Long maxId = statistics.getMaxId();

        return maxId <= offset;
    }

    private void saveToDb() {
        for (Row row : statistics.getRows()) {
            saveToDb(row);
        }
        saveOffset();
    }

    private void saveOffset() {
        config.getSource().saveOffset(config.getSinkName(), statistics.getMaxId());
    }

    private void saveToDb(Row row) {
        if (rowInvalid(row)) {
            return;
        }

        Set<String> keyColumns = config.getKeyColumns();
        Map<String, Object> keyMap = MapUtil.subMap(row, keyColumns);
        if (MapUtil.isEmpty(keyMap) || MapUtil.containsNull(keyMap)) {
            return;
        }

        Long id = isRowExists(keyMap);
        if (id == null) {
            addRow(row);
            return;
        }

        updateRow(row, id);
    }

    private Long isRowExists(Map<String, Object> keyMap) {
        Sql sql = Sql.exists(config.getTableName(),true)
                .where(keyMap)
                .limit(1);

        List<Long> idList = jdbc.queryForList(sql.getSql(), sql.getData(), Long.class);
        if (CollectionUtil.isEmpty(idList)) {
            return null;
        }

        return idList.get(0);
    }

    private void addRow(Row row) {
        Sql sql = Sql.insert(config.getTableName(), true)
                .values(row);

        jdbc.update(sql.getSql(), sql.getData());
    }

    private void updateRow(@NonNull Row row, @NonNull Long id) {
        MapUtil.remove(row, config.getKeyColumns());
        if (MapUtil.isEmpty(row, true)) {
            return;
        }

        Sql sql = Sql.update(config.getTableName(), true)
                .set(row)
                .where("id = ?", id)
                .limit(1);

        jdbc.update(sql.getSql(), sql.getData());
    }

    private boolean rowInvalid(Row row) {
        return MapUtil.isEmpty(row, true);
    }

}
