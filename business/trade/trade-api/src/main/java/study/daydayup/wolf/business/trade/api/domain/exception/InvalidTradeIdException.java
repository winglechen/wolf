package study.daydayup.wolf.business.trade.api.domain.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.trade.api.domain.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class InvalidTradeIdException extends SystemException {
    public InvalidTradeIdException(String msg) {
        super(160005, msg);
    }
}
