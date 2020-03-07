package study.daydayup.wolf.common.util.collection;

import com.google.common.base.Joiner;
import lombok.NonNull;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2020/1/14 3:58 下午
 **/
public class CollectionUtil {
    public static <E> boolean notEmpty(Collection<E> collection) {
        return null != collection && !collection.isEmpty();
    }

    public static <E> boolean isEmpty(Collection<E> collection) {
        return ! notEmpty(collection);
    }

    public  static <C, K> List<K> keys(Collection<C> collection, Function<C,K> getter) {
        return collection.stream()
                .map(getter)
                .collect(Collectors.toList());
    }

    public static <C, K> Map<K, C> map(Collection<C> collection, Function<C,K> getter) {
        return collection.stream().collect(
                Collectors.toMap(getter, Function.identity())
        );
    }

    public static <S extends Number, E> long sum(Collection<E> collection, Function<E, S> getter) {
        if (collection == null || null == getter || collection.isEmpty()) {
            return 0;
        }

        long result = 0;
        for (E e: collection) {
            S s = getter.apply(e);
            result += s.longValue();
        }

        return result;
    }

    public static <S extends Number, G, E> Map<G, Long> groupAndSum(Collection<E> collection, Function<E, G> grouper, Function<E, S> getter) {
        Map<G, List<E>> map = group(collection, grouper);
        if (map == null) {
            return null;
        }

        Map<G, Long> result = new HashMap<>();
        for (Map.Entry<G, List<E>> entry : map.entrySet()) {
            G key = entry.getKey();
            List<E> value = entry.getValue();

            result.put(key, sum(value, getter));
        }

        return result;
    }

    public static <G, E> Map<G, List<E>> group(Collection<E> collection, Function<E, G> getter) {
        if (collection == null || null == getter || collection.isEmpty()) {
            return null;
        }

        Map<G, List<E>> map = new HashMap<>();
        for (E c: collection) {
            G groupKey = getter.apply(c);
            List<E> list = map.get(groupKey);
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(c);
            map.put(groupKey, list);
        }

        return map;
    }

    public static <C1, MERGE_TYPE, C2> void join(Collection<C1> c1, Function<C1, MERGE_TYPE> getter, BiConsumer<C1, C2> setter, Collection<C2> c2, Function<C2, MERGE_TYPE> mGetter) {
        Map<MERGE_TYPE, C2> map = map(c2, mGetter);
        join(c1, getter, setter, map);
    }

    public static <C, MERGE_TYPE, M> void join(Collection<C> collection, Function<C, MERGE_TYPE> getter, BiConsumer<C, M> setter, Map<MERGE_TYPE, M> map) {
        for (C c : collection) {
            MERGE_TYPE k = getter.apply(c);
            M v = map.get(k);
            setter.accept(c, v);
        }
    }

    public static <Source, Target> List<Target> to(List<Source> sources, Function<Source, Target> convert) {
        if (sources == null || null == convert || sources.isEmpty()) {
            return null;
        }

        List<Target> result = new ArrayList<>();
        for (Source source : sources) {
            Target target = convert.apply(source);
            result.add(target);
        }

        return result;
    }

    public static <C> String join(@NonNull String delimiter, @NonNull Collection<C> collection) {
        if (collection.isEmpty()) {
            return "";
        }

        Joiner joiner = Joiner.on(delimiter).skipNulls();
        return joiner.join(collection);
    }



}
