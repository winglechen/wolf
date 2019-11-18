package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class GrantTypeNonsupportException extends BusinessException {

    public GrantTypeNonsupportException(String message) {
        super(message);
    }

    public GrantTypeNonsupportException(long code, String message) {
        super(code, message);
    }
}