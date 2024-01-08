package study.daydayup.wolf.framework.exception;

import lombok.Getter;
import study.daydayup.wolf.common.lang.exception.SystemException;

@Getter
public class WolfException extends SystemException {
    public WolfException(String message) {
        super(110, message);
    }

    public WolfException(long code, String message) {
        super(code, message);
    }
}
