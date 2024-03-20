package com.onedot.win.framework.rpc.http.client.cookie;

import com.onedot.win.common.lang.exception.SystemException;

public class InvalidCookieException extends SystemException {
    public InvalidCookieException() {
        super(500, "invalid cookie");
    }
}
