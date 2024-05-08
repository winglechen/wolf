package com.wolf.framework.exception;

import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.lang.string.Str;

public class BeanNotFoundException extends SystemException {
    public BeanNotFoundException(String className) {
        super(120, Str.join("Bean: ", className, " Not Found"));
    }
}
