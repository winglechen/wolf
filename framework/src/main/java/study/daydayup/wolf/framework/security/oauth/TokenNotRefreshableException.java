package study.daydayup.wolf.framework.security.oauth;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

public class TokenNotRefreshableException extends SystemException {
    public TokenNotRefreshableException() {
        super(800, "AccessToken is not refreshable");
    }

    public TokenNotRefreshableException(String msg) {
        super(800, Str.join("AccessToken is not refreshable: ", msg));
    }
}
