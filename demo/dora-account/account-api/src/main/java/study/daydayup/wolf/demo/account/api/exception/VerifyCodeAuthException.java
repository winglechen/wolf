package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class VerifyCodeAuthException extends BusinessException {

    public VerifyCodeAuthException(String message) {
        super(message);
    }

    public VerifyCodeAuthException(long code, String message) {
        super(code, message);
    }
}