package com.onedot.win.framework.middleware.ratelimit;

import com.onedot.win.common.lang.exception.SystemException;

public class RequestLimitedException extends SystemException {
    public RequestLimitedException() {
        super(500, "System busy, Please try again later");
    }
}
