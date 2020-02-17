package study.daydayup.wolf.framework.dts.source;

import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.framework.dts.config.SourceConfig;

/**
 * study.daydayup.wolf.framework.layer.task
 *
 * @author Wingle
 * @since 2020/2/5 11:20 上午
 **/
public interface Source {
    String DEFAULT_SHARDING_KEY = "root";
    OrderEnum DEFAULT_ORDER = OrderEnum.ASC;

    void init(SourceConfig config);
    Table getStream();
    Long getOffset();
    Long getOffset(String sinkName);
    void saveOffset(Long offset);
    void saveOffset(String sinkName, Long offset);
}
