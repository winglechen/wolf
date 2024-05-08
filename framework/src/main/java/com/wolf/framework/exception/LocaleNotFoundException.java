package com.wolf.framework.exception;

import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.lang.string.Str;

public class LocaleNotFoundException extends SystemException {
    public LocaleNotFoundException(String key) {
        super(120, Str.join("Locale: ", key, " Not Found"));
    }
}
