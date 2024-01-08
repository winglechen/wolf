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
public class OffsetNotAvailableException extends SystemException {
    public OffsetNotAvailableException(Offset offset) {
        super(1100, Str.join(
                "Offset not available:",
                JSON.toJSONString(offset)
        ));
    }
}
