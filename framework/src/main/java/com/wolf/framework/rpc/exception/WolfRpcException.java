package com.wolf.framework.rpc.exception;

import com.wolf.common.lang.exception.BusinessException;

/**
 * com.wolf.framework.rpc.exception
 *
 * @author Wingle
 * @since 2019/11/5 12:54 下午
 **/
public class WolfRpcException extends BusinessException{
    public WolfRpcException(String message) {
        super(message);
    }
}
