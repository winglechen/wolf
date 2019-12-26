package study.daydayup.wolf.business.trade.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.business.trade.api.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class TradeStateNotFoundException extends SystemException {
    public TradeStateNotFoundException(Integer state) {
        super(160004, Msg.join("Invalid trade state: ", state));
    }
}
