package study.daydayup.wolf.business.pay.api.domain.exception.payout;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class PayoutServiceNotFoundException extends SystemException {
    public PayoutServiceNotFoundException() {
        super(170000, "payout method fail");
    }

    public PayoutServiceNotFoundException(String msg) {
        super(170000, StringUtil.join("payout method: ", msg, " not found."));
    }
}
