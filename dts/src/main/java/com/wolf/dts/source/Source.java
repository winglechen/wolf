package com.wolf.dts.source;

import lombok.Builder;
import lombok.Data;
import com.wolf.common.lang.io.db.Table;
import com.wolf.common.lang.io.enums.OrderEnum;
import com.wolf.dts.source.offset.Offset;

/**
 * com.wolf.business.union.task.config
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
