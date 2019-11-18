package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class AuthorizationTypeNonsupportException extends BusinessException {

    public AuthorizationTypeNonsupportException(String message) {
        super(message);
    }

    public AuthorizationTypeNonsupportException(long code, String message) {
        super(code, message);
    }
}