package com.onedot.win.framework.rpc.exception;

import com.onedot.win.common.lang.exception.BusinessException;

/**
 * com.onedot.win.framework.rpc.exception
 *
 * @author Wingle
 * @since 2019/11/5 12:54 下午
 **/
public class WolfRpcException extends BusinessException{
    public WolfRpcException(String message) {
        super(message);
    }
}
