package com.wolf.framework.security.oauth;

import com.wolf.common.lang.exception.SystemException;
import com.wolf.common.lang.string.Str;

public class AccessTokenGenerateFailException extends SystemException {
    public AccessTokenGenerateFailException() {
        super(800, "AccessToken create fail");
    }

    public AccessTokenGenerateFailException(String msg) {
        super(800, Str.join("AccessToken create fail: ", msg));
    }
}
