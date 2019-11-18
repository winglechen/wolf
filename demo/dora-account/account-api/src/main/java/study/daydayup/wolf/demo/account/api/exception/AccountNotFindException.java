package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class AccountNotFindException extends BusinessException {

    public AccountNotFindException(String message) {
        super(message);
    }

    public AccountNotFindException(long code, String message) {
        super(code, message);
    }
}