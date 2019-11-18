package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class VerifyCodeSendErrorException extends BusinessException {

    public VerifyCodeSendErrorException(String message) {
        super(message);
    }

    public VerifyCodeSendErrorException(long code, String message) {
        super(code, message);
    }
}