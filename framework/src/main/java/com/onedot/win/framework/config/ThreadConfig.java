package com.onedot.win.framework.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.onedot.win.framework.layer.api.Config;

/**
 * com.onedot.win.framework.config
 *
 * @author Wingle
 * @since 2021/12/14 下午9:15
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadConfig implements Config {
    public static final int DEFAULT_MAX_THREAD_NUM = 20;
    public static final int DEFAULT_MIN_THREAD_NUM = 5;

    protected int minThreadNum = DEFAULT_MIN_THREAD_NUM;
    protected int maxThreadNum = DEFAULT_MAX_THREAD_NUM;
}
