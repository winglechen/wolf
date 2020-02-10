package study.daydayup.wolf.business.union.task.config;

import org.springframework.stereotype.Component;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/10 10:14 上午
 **/
@Component
public class OffsetConfig {
    private static final String TABLE_NAME = "offset_holder";

    public String getTable() {
        return TABLE_NAME;
    }
}
