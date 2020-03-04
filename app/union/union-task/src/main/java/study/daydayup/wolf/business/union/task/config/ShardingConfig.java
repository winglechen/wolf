package study.daydayup.wolf.business.union.task.config;

import org.springframework.stereotype.Component;

/**
 * study.daydayup.wolf.business.union.task.config
 *
 * @author Wingle
 * @since 2020/2/10 10:14 上午
 **/
@Component
public class ShardingConfig {
    private static final String DEFAULT_SHARD = "defaultShard";

    public String getShard() {
        return DEFAULT_SHARD;
    }

}
