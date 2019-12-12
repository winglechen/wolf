package study.daydayup.wolf.business.goods.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.goods.api.exception
 *
 * @author Wingle
 * @since 2019/12/12 10:31 上午
 **/
public class FailedCreateLoanException extends BusinessException {
    public FailedCreateLoanException() {
        super(140003, "Failed to create loan goods");
    }
}
