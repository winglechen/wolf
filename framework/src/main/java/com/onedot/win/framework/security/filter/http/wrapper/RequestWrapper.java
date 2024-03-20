package com.onedot.win.framework.security.filter.http.wrapper;

import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author weixing
 * @since 2022/11/15 19:45
 */
public class RequestWrapper extends ContentCachingRequestWrapper {

    private String requestBodyString = null;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getRequestBodyString() {
        if (requestBodyString == null) {
            byte[] requestBody = super.getContentAsByteArray();
            requestBodyString = new String(requestBody, StandardCharsets.UTF_8);
        }
        return requestBodyString;

    }

}
