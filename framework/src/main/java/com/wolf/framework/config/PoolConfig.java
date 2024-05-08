package com.wolf.framework.config;

import lombok.Data;
import com.wolf.framework.layer.api.Config;

/**
 * com.wolf.framework.config
 *
 * @author Wingle
 * @since 2021/12/14 下午9:04
 **/
@Data
public class PoolConfig implements Config {
    public static final int DEFAULT_MAX_TOTAL = 8;
    public static final int DEFAULT_MAX_IDLE = 8;
    public static final int DEFAULT_MIN_IDLE = 0;
    public static final int DEFAULT_INITIAL = 0;

    protected int initial = DEFAULT_INITIAL;
    protected int maxTotal = DEFAULT_MAX_TOTAL;

    protected int minIdle = DEFAULT_MIN_IDLE;
    protected int maxIdle = DEFAULT_MAX_IDLE;

}
