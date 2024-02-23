package study.daydayup.wolf.framework.middleware.binlog;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.io.db.Row;

/**
 * @author weixing
 * @since 2022/9/20 17:03
 */
@Data
@Builder
public class BinlogRow {
    private String sourceTable;
    private Long sourceTimestamp;
    private Row data;

    public static BinlogRow create(Row data, String sourceTable) {
        if (data == null) {
            return null;
        }
        return BinlogRow.builder()
                .sourceTable(sourceTable)
                .sourceTimestamp(System.currentTimeMillis())
                .data(data)
                .build();
    }
}