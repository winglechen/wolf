package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class AccountExistedException extends BusinessException {

    public AccountExistedException(String message) {
        super(message);
    }

    public AccountExistedException(long code, String message) {
        super(code, message);
    }
}