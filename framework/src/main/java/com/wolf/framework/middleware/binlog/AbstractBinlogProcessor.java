package com.wolf.framework.middleware.binlog;

import org.slf4j.Logger;
import com.wolf.common.io.db.Row;

import java.util.List;

/**
 * @author: YIK
 * @since: 2022/3/4 10:16 AM
 */
public abstract class AbstractBinlogProcessor implements BinlogProcessor {

    protected void logProcess(Logger log, String table, List<Row> rows, int affectRow) {
        Row lastRow = rows.get(rows.size() - 1);

        log.info("Subscribe source table: {} data BatchInsert to table: {} rows: {}, affectRow: {}, lastRowId: {}, lastRowCreatedAt: {}, lastRowUpdatedAt: {}, lastSourceTimestamp: {}",
                lastRow.getString("__source_table"),
                table,
                rows.size(),
                affectRow,
                lastRow.getId(),
                lastRow.get("created_at"),
                lastRow.get("updated_at"),
                lastRow.getLongValue("__source_timestamp")
        );

        if (rows.size() != affectRow) {
            log.warn("Duplicate on insertIgnore to table: {}, duplicate_count: {}", table, rows.size() - affectRow);
        }
    }

}
