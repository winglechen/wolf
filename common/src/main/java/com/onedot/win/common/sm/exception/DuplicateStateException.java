package com.onedot.win.common.sm.exception;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

/**
 * com.onedot.win.common.sm
 *
 * @author Wingle
 * @since 2019/12/16 11:15 下午
 **/
public class DuplicateStateException extends SystemException {
    public DuplicateStateException(String source, String target, String event) {
        super(1100, Str.join(
                "Duplicate State Map Found: {",
                "source: " , source,
                "; target:", target,
                "; event:", event,
                "}"
        ));
    }
}
