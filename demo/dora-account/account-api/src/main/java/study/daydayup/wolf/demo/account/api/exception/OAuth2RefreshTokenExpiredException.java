package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class OAuth2RefreshTokenExpiredException extends BusinessException {

    public OAuth2RefreshTokenExpiredException(String message) {
        super(message);
    }

    public OAuth2RefreshTokenExpiredException(long code, String message) {
        super(code, message);
    }
}
