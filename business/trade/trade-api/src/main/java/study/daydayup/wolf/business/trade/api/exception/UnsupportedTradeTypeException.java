package study.daydayup.wolf.business.trade.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.business.trade.buy.domain.exception
 *
 * @author Wingle
 * @since 2019/10/7 11:28 下午
 **/
public class UnsupportedTradeTypeException extends SystemException {
    public UnsupportedTradeTypeException() {
        this(0);
    }

    public UnsupportedTradeTypeException(Integer tradeType) {
        super(160001, Msg.join("unsupported tradeType: ", tradeType));
    }
}
