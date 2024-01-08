package study.daydayup.wolf.framework.middleware.ratelimit;

import study.daydayup.wolf.common.lang.exception.SystemException;

public class RequestLimitedException extends SystemException {
    public RequestLimitedException() {
        super(500, "System busy, Please try again later");
    }
}
