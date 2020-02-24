package study.daydayup.wolf.common.util.collection;

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
}
