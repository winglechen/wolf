package study.daydayup.wolf.middleware.notice.api.domain.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class SupplierNotFoundException extends SystemException {
    public SupplierNotFoundException() {
        super(124000, "No such supplier" );
    }
}
