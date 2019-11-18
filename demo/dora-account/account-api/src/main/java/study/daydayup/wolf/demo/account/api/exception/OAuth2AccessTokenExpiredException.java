package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class OAuth2AccessTokenExpiredException extends BusinessException {

    public OAuth2AccessTokenExpiredException(String message) {
        super(message);
    }

    public OAuth2AccessTokenExpiredException(long code, String message) {
        super(code, message);
    }
}
