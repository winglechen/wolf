package study.daydayup.wolf.common.lang.exception.enums;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.common.lang.exception.enums
 *
 * @author Wingle
 * @since 2019/9/29 11:26 PM
 **/
public class EnumCodeNotSupportException extends SystemException {
    public EnumCodeNotSupportException(String message) {
        super(message);
    }

    public EnumCodeNotSupportException(int code, String message) {
        super(code, message);
    }
}
