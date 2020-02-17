package study.daydayup.wolf.common.util.collection;

import java.util.Map;

/**
 * study.daydayup.wolf.common.util.collection
 *
 * @author Wingle
 * @since 2020/2/17 5:55 下午
 **/
public class MapUtil {
    public static <K, V> boolean hasValue(Map<K, V> map) {
        return null != map && 0 != map.size();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return ! hasValue(map);
    }


}
