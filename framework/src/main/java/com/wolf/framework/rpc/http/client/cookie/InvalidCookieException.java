package com.wolf.framework.rpc.http.client.cookie;

import com.wolf.common.lang.exception.SystemException;

public class InvalidCookieException extends SystemException {
    public InvalidCookieException() {
        super(500, "invalid cookie");
    }
}
