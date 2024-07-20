package com.wolf.dts.source;

import com.wolf.common.io.db.Table;
import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.io.sql.builder.SqlBuilder;

/**
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
