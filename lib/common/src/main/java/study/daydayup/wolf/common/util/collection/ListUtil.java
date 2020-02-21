package study.daydayup.wolf.common.util.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2020/1/17 9:15 下午
 **/
public class ListUtil {
    public static <E> List<E> empty(){
        return new ArrayList<>();
    }

    public static <E> boolean notEmpty(List<E> list) {
        return null != list && !list.isEmpty();
    }

    public static <E> boolean isEmpty(List<E> list) {
        return ! notEmpty(list);
    }

    public static <E> E last(List<E> list) {
        if (!notEmpty(list)) {
            return null;
        }

        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }
}
