package study.daydayup.wolf.business.goods.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/10/29 12:13 上午
 **/
public class GoodsNotFoundException extends BusinessException {
    public GoodsNotFoundException() {
        super(140001, "The goods doesn't exists.");
    }
}
