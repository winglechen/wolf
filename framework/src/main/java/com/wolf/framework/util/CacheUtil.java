package com.wolf.framework.util;

import com.wolf.common.util.lang.StringUtil;

/**
 * com.wolf.business.pay.api.config.cache
 *
 * @author Wingle
 * @since 2021/11/14 下午5:50
 **/
public class CacheUtil {

    public static String create(String prefix, String key, Object... keys) {
        if (StringUtil.isBlank(prefix) || StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("cache prefix, key can't be blank");
        }

        if (keys.length > 0) {
            return StringUtil.join(prefix, key, keys);
        }

        return StringUtil.join(prefix, key);
    }

    public static String createWithColon(String prefix, String key, Object... keys) {
        if (StringUtil.isBlank(prefix) || StringUtil.isBlank(key)) {
            throw new IllegalArgumentException("cache prefix, key can't be blank");
        }
        if (keys.length > 0) {
            return StringUtil.joinWith(StringUtil.COLON, prefix, key, keys);
        }
        return StringUtil.joinWith(StringUtil.COLON, prefix, key);
    }

}
