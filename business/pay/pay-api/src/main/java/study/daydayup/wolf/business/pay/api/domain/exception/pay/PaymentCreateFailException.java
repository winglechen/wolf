package study.daydayup.wolf.business.pay.api.domain.exception.pay;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class PaymentCreateFailException extends SystemException {
    public PaymentCreateFailException() {
        super(170000, "payment create fail");
    }

    public PaymentCreateFailException(String msg) {
        super(170000, StringUtil.join("payment create fail : ", msg));
    }
}
