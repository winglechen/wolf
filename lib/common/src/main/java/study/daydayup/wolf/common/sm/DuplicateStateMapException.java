package study.daydayup.wolf.common.sm;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:15 下午
 **/
public class DuplicateStateMapException extends SystemException {
    public DuplicateStateMapException(String source, String target, String event) {
        super(1100, Msg.join(
                "Duplicate State Map Found: {",
                "source: " , source,
                "; target:", target,
                "; event:", event,
                "}"
        ));
    }
}
