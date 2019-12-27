package study.daydayup.wolf.business.trade.api.exception.buy;

import study.daydayup.wolf.common.lang.exception.BusinessException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.business.trade.buy.domain.exception
 *
 * @author Wingle
 * @since 2019/10/7 11:28 下午
 **/
public class GoodsNotFoundException extends BusinessException {
    public GoodsNotFoundException() {
        this(null);
    }

    public GoodsNotFoundException(Long goodsId) {
        super(160501, Msg.join("Can't find goods: ", goodsId));
    }
}
