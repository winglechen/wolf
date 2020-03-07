package study.daydayup.wolf.business.pay.api.domain.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class DoublePayingException extends SystemException {
    public DoublePayingException(String msg) {
        super(170000, "order already paid: " +  msg);
    }
}
