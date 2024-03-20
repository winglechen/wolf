package com.onedot.win.framework.middleware.es.exception;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class InvalidESConfigException extends SystemException {
    public InvalidESConfigException(String message) {
        super(10500, "invalid es config: " + message);
    }

    public InvalidESConfigException() {
        super(10500, "invalid es config");
    }

}
