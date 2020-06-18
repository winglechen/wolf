package study.daydayup.wolf.business.pay.api.domain.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class TradeNoCreateFailException extends SystemException {
    public TradeNoCreateFailException() {
        super(170000, "fail to create tradeNo");
    }

    public TradeNoCreateFailException(String msg) {
        super(170000, "fail to create tradeNo:" + msg);
    }


}
