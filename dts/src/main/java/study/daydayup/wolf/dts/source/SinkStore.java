package study.daydayup.wolf.dts.source;

import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;

/**
 * study.daydayup.wolf.framework.layer.task
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
