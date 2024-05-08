package com.wolf.framework.exception;

import lombok.Getter;
import com.wolf.common.lang.exception.SystemException;

@Getter
public class WolfException extends SystemException {
    public WolfException(String message) {
        super(110, message);
    }

    public WolfException(long code, String message) {
        super(code, message);
    }
}
