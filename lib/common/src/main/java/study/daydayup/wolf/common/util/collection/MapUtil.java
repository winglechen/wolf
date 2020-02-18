package study.daydayup.wolf.common.util.collection;

import java.util.*;

/**
 * study.daydayup.wolf.common.util.collection
 *
 * @author Wingle
 * @since 2020/2/17 5:55 下午
 **/
public class MapUtil {
    public static <K, V> Map<K, V> empty() {
        return new HashMap<>();
    }

    public static <K, V> boolean hasValue(Map<K, V> map) {
        return null != map && 0 != map.size();
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return ! hasValue(map);
    }


    public static <K, V> boolean containsNull(Map<K, V> map) {
        return containsNullKey(map) || containsNullValue(map);
    }

    public static <K, V> boolean containsNullValue(Map<K, V> map) {
        return map.containsValue(null);
    }

    public static <K, V> boolean containsNullKey(Map<K, V> map) {
        return map.containsKey(null);
    }

    public static <K, V> Map<K, V> subMap(Map<K, V> map, Collection<K> keys) {
        return subMap(map, keys, false);
    }

    public static <K, V> Map<K, V> subMap(Map<K, V> map, Collection<K> keys, boolean skipNulls) {
        if (CollectionUtil.isEmpty(keys)) {
            return null;
        }

        Map<K, V> result = new HashMap<>();
        V v;
        for (K k : keys) {
            v = map.get(k);

            if (null == v && skipNulls) {
                continue;
            }

            result.put(k, v);
        }

        return result;
    }

    @SafeVarargs
    public static <K, V> Map<K, V> subMap(Map<K, V> map, boolean skipNulls, K... keys) {
        return subMap(map, Arrays.asList(keys), skipNulls);
    }

    @SafeVarargs
    public static <K, V> Map<K, V> subMap(Map<K, V> map, K... keys) {
        return subMap(map, Arrays.asList(keys));
    }
}
