package com.wolf.framework.security.sensitive;

import com.wolf.common.util.collection.MapUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author weixing
 * @date 2022/5/30 11:30
 */
public class SensitiveHttpHeaderFilter {
    private final static List<String> sensitiveHeaders = Arrays.asList(
            "X-IBM-Client-Secret",
            "Authorization"
    );

    public static Map<String, String> filter(Map<String, String> headers) {
        return MapUtil.exceptKeys(headers, sensitiveHeaders);
    }

    public static Map<String, String> erase(Map<String, String> headers) {
        return MapUtil.replaceValues(headers, sensitiveHeaders, "-");
    }
}