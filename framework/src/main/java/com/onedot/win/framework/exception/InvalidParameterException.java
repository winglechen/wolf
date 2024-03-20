package com.onedot.win.framework.exception;

import com.onedot.win.common.lang.exception.BusinessException;

/**
 * @author weixing
 * @date 2022/4/21 10:38
 */
public class InvalidParameterException extends BusinessException  {
    public InvalidParameterException(String message) {
        super(110400, message);
    }
}
