package com.onedot.win.framework.middleware.binlog;

import java.util.List;

/**
 * processor the binlog record
 *
 * @author: YIK
 * @since: 2022/3/4 10:08 AM
 */
public interface BinlogProcessor {
    BinlogConsumeTypeEnum consumeType();

    void onInsert(BinlogRow row);

    void onInsert(List<BinlogRow> rows);

    void onUpdate(BinlogRow row);

    void onUpdate(List<BinlogRow> rows);
}
