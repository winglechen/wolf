package study.daydayup.wolf.common.lang.exception;

/**
 * study.daydayup.wolf.common.lang.exception
 *
 * @author Wingle
 * @since 2019/9/29 5:47 PM
 **/
public class EnumNotFoundException extends SystemException {
    public EnumNotFoundException(String message) {
        super(message);
    }

    public EnumNotFoundException(long code, String message) {
        super(code, message);
    }
}
