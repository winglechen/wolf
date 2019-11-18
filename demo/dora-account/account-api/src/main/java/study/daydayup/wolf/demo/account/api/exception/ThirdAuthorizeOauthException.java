package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class ThirdAuthorizeOauthException extends BusinessException {

    public ThirdAuthorizeOauthException(String message) {
        super(message);
    }

    public ThirdAuthorizeOauthException(long code, String message) {
        super(code, message);
    }
}
