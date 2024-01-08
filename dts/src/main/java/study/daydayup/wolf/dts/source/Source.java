package study.daydayup.wolf.dts.source;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.dts.source.offset.Offset;

/**
 * study.daydayup.wolf.business.union.task.config
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
