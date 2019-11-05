package study.daydayup.wolf.framework.rpc.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.framework.rpc.exception
 *
 * @author Wingle
 * @since 2019/11/5 12:51 下午
 **/
public interface BusinessExceptionWrapper {
    void wrap(BusinessException e);

}
