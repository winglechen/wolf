package com.wolf.framework.exception;

import com.wolf.common.lang.exception.SystemException;

/**
 * Operation not support
 *
 * @author: YIK
 * @since: 2022/2/25 3:39 PM
 */
public class OperationNotSupportException extends SystemException {

    public OperationNotSupportException(String operate) {
        super(120, "Operate: " + operate + " not support");
    }
}
