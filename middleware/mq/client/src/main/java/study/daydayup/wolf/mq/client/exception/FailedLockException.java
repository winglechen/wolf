package study.daydayup.wolf.mq.client.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.mq.client.exception
 *
 * @author Wingle
 * @since 2019/12/2 5:35 下午
 **/
public class FailedLockException extends BusinessException {
    public FailedLockException(String message) {
        super(message);
    }

    public FailedLockException(long code, String message) {
        super(code, message);
    }
}
