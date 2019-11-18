package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class OAuth2RefreshTokenErrorException extends BusinessException {

    public OAuth2RefreshTokenErrorException(String message) {
        super(message);
    }

    public OAuth2RefreshTokenErrorException(long code, String message) {
        super(code, message);
    }
}
