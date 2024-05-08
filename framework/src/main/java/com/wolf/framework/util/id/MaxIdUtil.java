package com.wolf.framework.util.id;

import lombok.NonNull;

/**
 * com.wolf.framework.util.id
 *
 * @author Wingle
 * @since 2021/11/15 下午10:20
 **/
public class MaxIdUtil {
    public static Long minus(@NonNull Long maxId, @NonNull Long offset) {
        if (offset <= 0L) {
            return maxId;
        }

        long diff = maxId - offset;
        return Math.max(diff, 0L);
    }

}
