package study.daydayup.wolf.framework.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * @author weixing
 * @date 2022/4/21 10:38
 */
public class InvalidParameterException extends BusinessException  {
    public InvalidParameterException(String message) {
        super(110400, message);
    }
}
