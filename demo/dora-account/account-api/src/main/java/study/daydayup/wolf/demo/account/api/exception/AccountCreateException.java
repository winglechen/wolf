package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class AccountCreateException extends BusinessException {

    public AccountCreateException(String message) {
        super(message);
    }

    public AccountCreateException(long code, String message) {
        super(code, message);
    }
}