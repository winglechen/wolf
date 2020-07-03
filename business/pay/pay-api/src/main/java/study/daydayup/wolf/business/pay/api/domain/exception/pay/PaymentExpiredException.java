package study.daydayup.wolf.business.pay.api.domain.exception.pay;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class PaymentExpiredException extends SystemException {
    public PaymentExpiredException() {
        super(170500, "payment expired");
    }

    public PaymentExpiredException(String msg) {
        super(170500, StringUtil.join("payment expired : ", msg));
    }
}
