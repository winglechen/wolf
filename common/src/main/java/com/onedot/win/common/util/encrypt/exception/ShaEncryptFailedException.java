package com.onedot.win.common.util.encrypt.exception;

import lombok.Getter;
import com.onedot.win.common.lang.exception.SystemException;

@Getter
public class ShaEncryptFailedException extends SystemException {
    public ShaEncryptFailedException(String message) {
        super(message);
    }
}
