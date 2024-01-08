package study.daydayup.wolf.framework.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

public class LocaleNotFoundException extends SystemException {
    public LocaleNotFoundException(String key) {
        super(120, Str.join("Locale: ", key, " Not Found"));
    }
}
