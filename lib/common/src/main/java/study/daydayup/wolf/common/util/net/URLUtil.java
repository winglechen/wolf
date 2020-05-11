package study.daydayup.wolf.common.util.net;

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

    public static Map<String, String> parseQuery(String query) throws UnsupportedEncodingException {
        return parseQuery(query, true, CharsetUtil.UTF_8.toString());
    }

    public static Map<String, String> parseQuery(String query, boolean decode, String enc) throws UnsupportedEncodingException {
        Map<String, String> result = new HashMap<>(8);
        if (StringUtil.isBlank(query)) {
            return result;
        }

        if (decode) {
            query = URLDecoder.decode(query, enc);
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

            key = kvArr[0].trim();
            value = kvArr[1].trim();
            result.put(key, value);
        }

        return result;
    }

}
