package com.onedot.win.dts.source;

import lombok.Builder;
import lombok.Data;
import com.onedot.win.common.io.db.Table;
import com.onedot.win.common.io.enums.OrderEnum;
import com.onedot.win.dts.source.offset.Offset;

/**
 * com.onedot.win.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:06 下午
 **/
@Data
@Builder
public class Source {
    private String source;
    private String sink;

    private String table;
    private String shard;

    private Offset offset;

    private String columns;
    private OrderEnum order;

    public String getShard() {
        if (shard == null) {
            return SinkStore.DEFAULT_SHARDING_KEY;
        }

        return shard;
    }

    public String getColumns() {
        if (columns == null) {
            return Table.ALL_COLUMNS;
        }

        return columns;
    }

    public OrderEnum getOrder() {
        if (order == null) {
            return SinkStore.DEFAULT_ORDER;
        }

        return order;
    }

}
