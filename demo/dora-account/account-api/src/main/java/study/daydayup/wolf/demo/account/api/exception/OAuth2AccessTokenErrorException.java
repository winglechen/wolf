package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class OAuth2AccessTokenErrorException extends BusinessException {

    public OAuth2AccessTokenErrorException(String message) {
        super(message);
    }

    public OAuth2AccessTokenErrorException(long code, String message) {
        super(code, message);
    }
}
