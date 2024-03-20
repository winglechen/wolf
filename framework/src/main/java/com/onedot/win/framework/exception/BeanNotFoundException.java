package com.onedot.win.framework.exception;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

public class BeanNotFoundException extends SystemException {
    public BeanNotFoundException(String className) {
        super(120, Str.join("Bean: ", className, " Not Found"));
    }
}
