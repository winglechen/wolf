package study.daydayup.wolf.common.sm;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:29 下午
 **/
public class StateNotFoundException extends SystemException {
    public StateNotFoundException(String source, String event) {
        super(1100, Str.join(
                "Can't find State Map: {",
                "source: " , source,
                "; event:", event,
                "}"
        ));
    }
}
