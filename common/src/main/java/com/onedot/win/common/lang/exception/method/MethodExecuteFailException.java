package com.onedot.win.common.lang.exception.method;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class MethodExecuteFailException extends SystemException {
    private static final String DEFAULT_MESSAGE = "Null returned exception";
    public MethodExecuteFailException() {
        super(100501, DEFAULT_MESSAGE);
    }

    public MethodExecuteFailException(String message) {
        super(100501, message);
    }

    public MethodExecuteFailException(long code, String message) {
        super(code, message);
    }
}
