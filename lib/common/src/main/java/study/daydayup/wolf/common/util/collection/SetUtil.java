package study.daydayup.wolf.common.util.collection;

import java.util.Set;

/**
 * study.daydayup.wolf.common.util.collection
 *
 * @author Wingle
 * @since 2020/2/18 10:36 上午
 **/
public class SetUtil {
    public static <E> boolean isEmpty(Set<E> set) {
        return null == set || set.isEmpty();
    }

    public static <E> boolean notEmpty(Set<E> set) {
        return ! isEmpty(set);
    }
}
