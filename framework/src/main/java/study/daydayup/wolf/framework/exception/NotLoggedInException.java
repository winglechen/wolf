package study.daydayup.wolf.framework.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class NotLoggedInException extends BusinessException {

    public NotLoggedInException(String message) {
        super(110500, message);
    }

}
