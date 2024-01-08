package study.daydayup.wolf.dts.sink;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.io.sql.util.SqlUtil;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.collection.SetUtil;
import study.daydayup.wolf.dts.source.offset.Offset;
import study.daydayup.wolf.dts.source.offset.OffsetGateway;
import study.daydayup.wolf.dts.transformer.Statistics;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * study.daydayup.wolf.dts.sink
 *
 * @author Wingle
 * @since 2020/2/16 6:59 下午
 **/
@Slf4j
@Component
public class MysqlSink implements SinkStore {
    @Resource
    private JdbcTemplate jdbc;
    @Resource
    private MysqlSink mysqlSink;
    @Resource
    private OffsetGateway offsetGateway;

    @Override
    public void save(Sink sink) {
        Statistics statistics = sink.getStatistics();
        if (null == statistics || CollectionUtil.isEmpty(statistics.getAggregateRows())) {
            mysqlSink.saveOffset(sink);
            return;
        }

        if (configInvalid(sink)) {
            return;
        }

        if (offsetInvalid(statistics, sink)) {
            return;
        }

        mysqlSink.saveToDb(statistics, sink);
    }

    private boolean configInvalid(Sink sink) {
        if (null == sink || null == sink.getOffset()) {
            return true;
        }

        if (null == sink.getSinkName()) {
            return true;
        }

        return SetUtil.isEmpty(sink.getGroupFields());
    }

    private boolean offsetInvalid(Statistics statistics, Sink sink) {
        if (sink.getOffset() == null) {
            return true;
        }

        Long maxId = statistics.getMaxOffsetId();
        return maxId <= sink.getOffset().getOffset();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveToDb(Statistics statistics, Sink sink) {
        if (saveOffset(sink) <= 0) {
            return;
        }

        for (Row row : statistics.getAggregateRows()) {
            mysqlSink.saveRow(row, sink);
        }
    }

    public int saveOffset(Sink sink) {
        Offset offset = sink.getOffset();

        Statistics statistics = sink.getStatistics();
        if (null != statistics && null != statistics.getMaxOffsetId()) {
            offset.setNewOffset(statistics.getMaxOffsetId());
        }

        return offsetGateway.set(sink.getOffset());
    }

    public void saveRow(Row row, Sink sink) {
        if (MapUtil.isEmpty(row, true)) {
            return;
        }

        Set<String> keyColumns = sink.getGroupFields();
        Map<String, Object> keyMap = MapUtil.subMap(row, keyColumns);
        if (MapUtil.isEmpty(keyMap) || MapUtil.containsNull(keyMap)) {
            return;
        }

        //TODO check ON DUPLICATE KEY UPDATE
        //insertOrUpdate(keyMap, row, sink);
        mysqlSink.checkAndSave(keyMap, row, sink);
    }

    private void insertOrUpdate(Map<String, Object> keyMap, Row row, Sink sink) {
        Map<String, Object> update = MapUtil.difference(row, keyMap);
        SqlBuilder sql = Sql.insert(sink.getTableName(), true)
                .values(row)
                .duplicateUpdate(update);

        jdbc.update(sql.getSql(), sql.getData());
    }

    public void checkAndSave(Map<String, Object> keyMap, Row row, Sink sink) {
        Long id = isRowExists(keyMap, sink);
        if (id == null) {
            addRow(row, sink);
            return;
        }

        updateRow(row, id, sink);
    }

    private Long isRowExists(Map<String, Object> keyMap, Sink sink) {
        SqlBuilder sql = Sql.exists(sink.getTableName(), true)
                .where(keyMap)
                .limit(1);

        List<Long> idList = jdbc.queryForList(sql.getSql(), sql.getData(), Long.class);
        if (CollectionUtil.isEmpty(idList)) {
            return null;
        }

        return idList.get(0);
    }

    private void addRow(Row row, Sink sink) {
        SqlBuilder sql = Sql.insert(sink.getTableName(), true)
                .values(row);

        log.info("datav insert: {}", sql.getSql());
        log.info("datav insert data: {}", row);
        jdbc.update(sql.getSql(), sql.getData());
    }

    private void updateRow(@NonNull Row row, @NonNull Long id, Sink sink) {
        MapUtil.remove(row, sink.getGroupFields());
        if (MapUtil.isEmpty(row, true)) {
            return;
        }

        SqlBuilder sql = Sql.update(sink.getTableName(), true)
                .set(SqlUtil.toAdd(row))
                .where("id = ?", id)
                .limit(1);

        log.info("datav update: {}", sql.getSql());
        log.info("datav update data: {}", row);
        jdbc.update(sql.getSql(), sql.getData());
    }

}
