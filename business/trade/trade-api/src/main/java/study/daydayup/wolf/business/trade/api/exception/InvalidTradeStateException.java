package study.daydayup.wolf.business.trade.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.business.trade.api.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class InvalidTradeStateException extends SystemException {
    public InvalidTradeStateException(Integer state) {
        super(160003, Msg.join("Invalid trade state: ", state));
    }
}
