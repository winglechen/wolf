package study.daydayup.wolf.dts.source.offset;

import com.alibaba.fastjson.JSON;
import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:29 下午
 **/
public class OffsetLockFailException extends SystemException {
    public OffsetLockFailException(Offset offset) {
        super(1100, Str.join(
                "Offset lock fail:",
                JSON.toJSONString(offset)
        ));
    }
}
