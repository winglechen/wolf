package study.daydayup.wolf.framework.middleware.es.exception;

import lombok.Getter;
import study.daydayup.wolf.common.lang.exception.SystemException;

@Getter
public class InvalidESConfigException extends SystemException {
    public InvalidESConfigException(String message) {
        super(10500, "invalid es config: " + message);
    }

    public InvalidESConfigException() {
        super(10500, "invalid es config");
    }

}
