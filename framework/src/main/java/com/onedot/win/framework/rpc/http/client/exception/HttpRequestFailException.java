package com.onedot.win.framework.rpc.http.client.exception;

import com.onedot.win.common.lang.exception.SystemException;
import com.onedot.win.common.lang.string.Str;

public class HttpRequestFailException extends SystemException {
    public HttpRequestFailException(String msg) {
        super(500, Str.join("HttpClient request fail: ", msg));
    }
}
