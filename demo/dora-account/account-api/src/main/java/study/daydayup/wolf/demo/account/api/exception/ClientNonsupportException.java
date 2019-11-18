package study.daydayup.wolf.demo.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

public class ClientNonsupportException extends BusinessException {

    public ClientNonsupportException(String message) {
        super(message);
    }

    public ClientNonsupportException(long code, String message) {
        super(code, message);
    }
}