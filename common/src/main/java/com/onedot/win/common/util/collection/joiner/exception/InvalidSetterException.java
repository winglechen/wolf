package com.onedot.win.common.util.collection.joiner.exception;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class InvalidSetterException extends SystemException {
    public InvalidSetterException(String message) {
        super(message);
    }

    public InvalidSetterException() {
        super("Invalid setter for CollectionJoiner");
    }
}
