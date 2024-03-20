package com.onedot.win.framework.exception;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

public class LocaleNotFoundException extends SystemException {
    public LocaleNotFoundException(String key) {
        super(120, Str.join("Locale: ", key, " Not Found"));
    }
}
