package study.daydayup.wolf.framework.security.oauth;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

public class AccessTokenGenerateFailException extends SystemException {
    public AccessTokenGenerateFailException() {
        super(800, "AccessToken create fail");
    }

    public AccessTokenGenerateFailException(String msg) {
        super(800, Str.join("AccessToken create fail: ", msg));
    }
}
