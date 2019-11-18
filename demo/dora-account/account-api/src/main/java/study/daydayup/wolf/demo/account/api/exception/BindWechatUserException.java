package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class BindWechatUserException extends BusinessException {

    public BindWechatUserException(String message) {
        super(message);
    }

    public BindWechatUserException(long code, String message) {
        super(code, message);
    }
}