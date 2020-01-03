package study.daydayup.wolf.common.lang.exception.api;

import lombok.Getter;
import study.daydayup.wolf.common.lang.exception.SystemException;

@Getter
public class NullReturnedException extends SystemException {
    public NullReturnedException(String message) {
        super(message);
    }

    public NullReturnedException(long code, String message) {
        super(code, message);
    }
}
