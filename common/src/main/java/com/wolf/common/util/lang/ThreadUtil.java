package com.wolf.common.util.lang;

import lombok.extern.slf4j.Slf4j;
import com.wolf.common.lang.exception.lang.InterruptedException;

/**
 * com.wolf.common.util.lang
 *
 * @author Wingle
 * @since 2022/1/25 上午8:06
 **/
@Slf4j
public class ThreadUtil {
    public static boolean sleep(long millis) {
        return ThreadUtil.sleep(millis, null, null);
    }

    public static boolean sleep(long millis, String msg) {
        return sleep(millis, null, msg);
    }

    public static boolean sleep(long millis, Integer nanos, String msg) {
        if (StringUtil.isBlank(msg)) {
            msg = "thread.sleep interrupted";
        }

        try {
            if (nanos != null) {
                Thread.sleep(millis, nanos);
            } else {
                Thread.sleep(millis);
            }
        } catch (Exception e) {
            log.error(msg, e);
            return false;
        }

        return true;
    }
}
