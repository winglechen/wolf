package com.wolf.framework.exception;

import com.wolf.common.lang.exception.BusinessException;

/**
 * @author weixing
 * @date 2022/4/21 10:38
 */
public class InvalidParameterException extends BusinessException  {
    public InvalidParameterException(String message) {
        super(110400, message);
    }
}
