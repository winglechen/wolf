package study.daydayup.wolf.business.goods.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 2:04 下午
 **/
public class InvalidGoodsIdException extends SystemException {
    public InvalidGoodsIdException(Long goodsId) {
        super(140004, "Invalid goodsId: " + goodsId);
    }
}
