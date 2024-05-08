package com.wolf.framework.exception;

import com.wolf.common.lang.exception.BusinessException;

/**
 * @author weixing
 * @since 2022/4/21 10:38
 */
public class InvalidRequestException extends BusinessException  {
    public InvalidRequestException(String message) {
        super(110400, message);
    }
}
