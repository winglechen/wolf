package study.daydayup.wolf.demo.ali.provider;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.demo.ali.api.exception
 *
 * @author Wingle
 * @since 2019/11/4 8:21 下午
 **/
public class HelloBizException extends BusinessException {
    public HelloBizException(String message) {
        super(message);
    }
}
