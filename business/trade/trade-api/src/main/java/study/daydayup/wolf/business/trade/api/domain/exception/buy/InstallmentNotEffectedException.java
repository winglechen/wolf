package study.daydayup.wolf.business.trade.api.domain.exception.buy;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.trade.buy.domain.exception
 *
 * @author Wingle
 * @since 2019/10/7 11:28 下午
 **/
public class InstallmentNotEffectedException extends BusinessException {
    public InstallmentNotEffectedException() {
        super(160504, "Installment is not effected");
    }
}
