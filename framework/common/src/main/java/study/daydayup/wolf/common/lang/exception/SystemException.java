package study.daydayup.wolf.common.lang.exception;

import lombok.Getter;

@Getter
public class SystemException extends BaseException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(long code, String message) {
        super(code, message);
    }
}
