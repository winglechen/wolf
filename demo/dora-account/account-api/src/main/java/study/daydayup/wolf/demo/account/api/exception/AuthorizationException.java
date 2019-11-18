package study.daydayup.wolf.demo.account.api.exception;
import study.daydayup.wolf.common.lang.exception.BusinessException;

public class AuthorizationException  extends BusinessException {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(long code, String message) {
        super(code, message);
    }
}