package study.daydayup.wolf.business.org.api.task.domain.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/10/29 12:13 上午
 **/
public class TaskNotFoundException extends BusinessException {
    public TaskNotFoundException() {
        super(130000, "The task doesn't exists.");
    }
}
