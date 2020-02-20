package study.daydayup.wolf.dts.source;

import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.dts.config.SourceConfig;

/**
 * study.daydayup.wolf.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:20 上午
 **/
public interface Source {
    String DEFAULT_SHARDING_KEY = "root";
    OrderEnum DEFAULT_ORDER = OrderEnum.ASC;

    Source init(SourceConfig config);
    Table getStream();
    Table getStream(String sinkName);
    Long getOffset();
    Long getOffset(String sinkName);
    int saveOffset(Long newOffset);
    int saveOffset(String sinkName, Long newOffset);
}
