package study.daydayup.wolf.common.lang.exception;

import lombok.Getter;

@Getter
public class NullReturnedException extends SystemException {
    public NullReturnedException(String message) {
        super(message);
    }

    public NullReturnedException(long code, String message) {
        super(code, message);
    }
}
