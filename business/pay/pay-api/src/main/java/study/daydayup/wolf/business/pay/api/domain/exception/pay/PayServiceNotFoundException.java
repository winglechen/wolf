package study.daydayup.wolf.business.pay.api.domain.exception.pay;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class PayServiceNotFoundException extends SystemException {
    public PayServiceNotFoundException() {
        super(170000, "payment method fail");
    }

    public PayServiceNotFoundException(String msg) {
        super(170000, StringUtil.join("payment method: ", msg, " not found."));
    }
}
