package study.daydayup.wolf.common.util;

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

    public static <E> boolean hasValue(List<E> list) {
        return null != list && !list.isEmpty();
    }
}
