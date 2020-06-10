package study.daydayup.wolf.common.util.net;

import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.CharsetUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.common.util.lang
 *
 * @author Wingle
 * @since 2020/5/7 7:39 下午
 **/
public class URLUtil {

    public static Map<String, String> parseQuery(String query) {
        try {
            return parseQuery(query, true, CharsetUtil.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return MapUtil.empty();
        }
    }

    public static Map<String, String> parseQuery(String query, boolean decode, String enc) throws UnsupportedEncodingException {
        Map<String, String> result = new HashMap<>(8);
        if (StringUtil.isBlank(query)) {
            return result;
        }

        String[] sArr = StringUtil.split(query, "&");
        String[] kvArr;
        String key, value;
        for (String s: sArr) {
            if (StringUtil.isBlank(s)) {
                continue;
            }
            kvArr = StringUtil.split(s, "=");
            if (2 != kvArr.length) {
                continue;
            }

            key = kvArr[0];
            value = kvArr[1];
            if (decode) {
                value = URLDecoder.decode(value, enc);
            }
            result.put(key, value);
        }

        return result;
    }

}
