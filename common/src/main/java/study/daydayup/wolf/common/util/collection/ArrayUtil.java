package study.daydayup.wolf.common.util.collection;

import study.daydayup.wolf.common.util.lang.BeanUtil;

/**
 * study.daydayup.wolf.common.util.collection
 *
 * @author Wingle
 * @since 2020/2/18 7:31 上午
 **/
public class ArrayUtil {
    public static <T> boolean isEmpty(T[] tArray) {
        return null == tArray || 0 == tArray.length;
    }

    public static <T> boolean notEmpty(T[] tArray) {
        return ! isEmpty(tArray);
    }

    @SafeVarargs
    public static <T> boolean inArray(T t, T... array) {
        if (isEmpty(array)) {
            return false;
        }

        for (T tItem : array) {
            if (BeanUtil.equals(t, tItem)) {
                return true;
            }
        }

        return false;
    }

    @SafeVarargs
    public static <T> boolean notInArray(T t, T... array) {
        return !inArray(t, array);
    }
}
