package study.daydayup.wolf.framework.rpc.http.client.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Str;

public class HttpRequestFailException extends SystemException {
    public HttpRequestFailException(String msg) {
        super(500, Str.join("HttpClient request fail: ", msg));
    }
}
