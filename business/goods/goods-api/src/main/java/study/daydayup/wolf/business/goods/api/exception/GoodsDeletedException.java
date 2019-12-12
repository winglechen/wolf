package study.daydayup.wolf.business.goods.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/10/29 12:13 上午
 **/
public class GoodsDeletedException extends BusinessException {
    public GoodsDeletedException() {
        super(140002, "The goods was deleted.");
    }
}
