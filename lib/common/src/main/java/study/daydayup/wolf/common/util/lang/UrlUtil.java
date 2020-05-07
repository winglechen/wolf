package study.daydayup.wolf.common.util.lang;

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
public class UrlUtil {

    public static Map<String, String> parseUrl(String url) throws UnsupportedEncodingException {
        return parseUrl(url, true, CharsetUtil.UTF_8.toString());
    }

    public static Map<String, String> parseUrl(String url, boolean decode, String enc) throws UnsupportedEncodingException {
        Map<String, String> result = new HashMap<>(8);
        if (StringUtil.isBlank(url)) {
            return result;
        }

        if (decode) {
            url = URLDecoder.decode(url, enc);
        }

        String[] sArr = StringUtil.split(url, "&");
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
