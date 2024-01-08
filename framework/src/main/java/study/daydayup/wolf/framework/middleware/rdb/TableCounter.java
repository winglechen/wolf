package study.daydayup.wolf.framework.middleware.rdb;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.io.db.Table;

@Slf4j
public class TableCounter {
    public static Long getRowCount(@NonNull String tableName, @NonNull Long accountId) {
        Table table = RDB.select("row_count")
                .from("table_counter")
                .where("table_name", "=", tableName)
                .and("account_id", "=", accountId)
                .orderBy("id")
                .limit(1)
                .execute()
                .getTable();

        log.info("TableCounter: getRowCount: {}", table);

        if (table.size() == 0) {
            return null;
        }

        return table.getFirst().getLong("row_count");
    }
}
