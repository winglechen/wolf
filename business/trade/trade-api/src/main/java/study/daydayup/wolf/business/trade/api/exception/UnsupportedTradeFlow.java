package study.daydayup.wolf.business.trade.api.exception;

import study.daydayup.wolf.framework.exception.BusinessException;

/**
 * study.daydayup.wolf.business.trade.buy.domain.exception
 *
 * @author Wingle
 * @since 2019/10/7 11:28 下午
 **/
public class UnsupportedTradeFlow extends BusinessException {
    public UnsupportedTradeFlow(String message) {
        super(message);
    }

    public UnsupportedTradeFlow(long code, String message) {
        super(code, message);
    }
}
