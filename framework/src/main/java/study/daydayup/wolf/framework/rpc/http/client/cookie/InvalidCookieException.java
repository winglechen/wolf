package study.daydayup.wolf.framework.rpc.http.client.cookie;

import study.daydayup.wolf.common.lang.exception.SystemException;

public class InvalidCookieException extends SystemException {
    public InvalidCookieException() {
        super(500, "invalid cookie");
    }
}
