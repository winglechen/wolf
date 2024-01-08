package study.daydayup.wolf.framework.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

public class BeanNotFoundException extends SystemException {
    public BeanNotFoundException(String className) {
        super(120, Str.join("Bean: ", className, " Not Found"));
    }
}
