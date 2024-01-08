package study.daydayup.wolf.dts.source.offset;


import lombok.NonNull;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.framework.layer.dal.scanner
 *
 * @author Wingle
 * @since 2020/2/5 5:27 下午
 **/
public interface OffsetHolder {

    Long get(Offset offset);
    Long read(Offset offset);
    int set(Offset offset);

    void lock(Offset offset);
    Long increase(Offset offset, long num);
    Long decrease(Offset offset, long num);

    Long lockAndIncrease(Offset offset, long num);
    Long lockAndDecrease(Offset offset, long num);

    default String formatKey(@NonNull Offset offset) {
        return StringUtil.joinWith(StringUtil.COLON, offset.getSource(), offset.getTable(), offset.getShard(), offset.getSink());
    }
}
