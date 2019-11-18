package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class BindAlipayUserException extends BusinessException {

    public BindAlipayUserException(String message) {
        super(message);
    }

    public BindAlipayUserException(long code, String message) {
        super(code, message);
    }
}