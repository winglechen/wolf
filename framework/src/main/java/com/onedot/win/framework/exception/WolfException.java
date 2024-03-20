package com.onedot.win.framework.exception;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class WolfException extends SystemException {
    public WolfException(String message) {
        super(110, message);
    }

    public WolfException(long code, String message) {
        super(code, message);
    }
}
