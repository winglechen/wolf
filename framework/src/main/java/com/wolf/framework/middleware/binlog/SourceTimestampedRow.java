package com.wolf.framework.middleware.binlog;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.wolf.common.io.db.Row;

/**
 * wrap row with sourceTimestamp
 *
 * @author: YIK
 * @since: 2022/5/7 6:01 PM
 */

@Data
@AllArgsConstructor
public class SourceTimestampedRow {
    private long sourceTimestamp;
    private Row row;
}
