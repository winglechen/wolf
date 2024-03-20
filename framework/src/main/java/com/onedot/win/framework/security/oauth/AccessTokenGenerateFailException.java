package com.onedot.win.framework.security.oauth;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

public class AccessTokenGenerateFailException extends SystemException {
    public AccessTokenGenerateFailException() {
        super(800, "AccessToken create fail");
    }

    public AccessTokenGenerateFailException(String msg) {
        super(800, Str.join("AccessToken create fail: ", msg));
    }
}
