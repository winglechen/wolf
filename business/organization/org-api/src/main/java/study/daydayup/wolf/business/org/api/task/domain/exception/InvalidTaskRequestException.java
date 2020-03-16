package study.daydayup.wolf.business.org.api.task.domain.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/10/29 12:13 上午
 **/
public class InvalidTaskRequestException extends BusinessException {
    public InvalidTaskRequestException() {
        super(130000, "Invalid Task Request");
    }

    public InvalidTaskRequestException(String msg) {
        super(130000, msg);
    }
}
