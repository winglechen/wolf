package study.daydayup.wolf.framework.dts.config;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.common.io.enums.OrderEnum;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/16 4:06 下午
 **/
@Data
@Builder
public class SourceConfig {
    private String sourceName;

    private String tableName;
    private String shardingKey;

    private String columns;
    private OrderEnum order;

}
