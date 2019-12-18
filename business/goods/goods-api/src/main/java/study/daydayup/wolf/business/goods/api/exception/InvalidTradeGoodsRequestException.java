package study.daydayup.wolf.business.goods.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class InvalidTradeGoodsRequestException extends SystemException {
    public InvalidTradeGoodsRequestException(String msg) {
        super(140901, "Invalid trade goods request: " + msg);
    }
}
