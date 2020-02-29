package study.daydayup.wolf.business.pay.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class EpiTimeoutException extends SystemException {
    public EpiTimeoutException(String msg) {
        super(170000, "Request timeout: " +  msg);
    }
}
