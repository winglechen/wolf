package study.daydayup.wolf.framework.rpc.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.framework.rpc.exception
 *
 * @author Wingle
 * @since 2019/11/5 12:54 下午
 **/
public class WolfRpcException extends BusinessException{
    public WolfRpcException(String message) {
        super(message);
    }
}
