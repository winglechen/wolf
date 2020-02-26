package study.daydayup.wolf.common.util.lang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import study.daydayup.wolf.common.util.collection.ArrayUtil;

/**
 * study.daydayup.wolf.common.util.lang
 *
 * @author Wingle
 * @since 2020/2/26 1:00 下午
 **/
public class JsonUtil {
    private static final String KEY_SEPARATOR = ".";

    public static JSONObject parse(@NonNull String str) {
        return JSON.parseObject(str);
    }

    public static <T> T parse(@NonNull String str, Class<T> clazz) {
        return JSON.parseObject(str, clazz);
    }

    public static JSONObject getJSONObject(@NonNull JSONObject obj, @NonNull String keyString) {
        String[] keys = StringUtil.split(keyString, KEY_SEPARATOR);
        return getJSONObject(obj, keys);
    }

    public static JSONObject getJSONObject(@NonNull JSONObject obj, String... keys) {
        if (ArrayUtil.isEmpty(keys)) {
            return null;
        }

        JSONObject tmp = obj;
        for (String key : keys) {
            tmp = tmp.getJSONObject(key);
            if (0 == tmp.size()) {
                return null;
            }
        }

        return tmp;
    }
}
