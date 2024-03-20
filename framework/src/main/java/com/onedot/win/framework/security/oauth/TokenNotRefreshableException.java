package com.onedot.win.framework.security.oauth;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

public class TokenNotRefreshableException extends SystemException {
    public TokenNotRefreshableException() {
        super(800, "AccessToken is not refreshable");
    }

    public TokenNotRefreshableException(String msg) {
        super(800, Str.join("AccessToken is not refreshable: ", msg));
    }
}
