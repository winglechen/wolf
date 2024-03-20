package com.onedot.win.dts.source;

import com.onedot.win.common.io.db.Table;
import com.onedot.win.common.io.enums.OrderEnum;
import com.onedot.win.common.io.sql.builder.SqlBuilder;

/**
 * com.onedot.win.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:20 上午
 **/
public interface SinkStore {
    String DEFAULT_SHARDING_KEY = "root";
    OrderEnum DEFAULT_ORDER = OrderEnum.ASC;

    Table getStream(Source config);

    Table select(String sql);
    Table select(SqlBuilder sqlBuilder);
}
