package study.daydayup.wolf.framework.middleware.binlog.config;

import lombok.Data;

/**
 * @author weixing
 * @since 2023/3/2 19:55
 */
@Data
public class Checkpoint {
    private Long timestamp;
    private boolean force;
}
