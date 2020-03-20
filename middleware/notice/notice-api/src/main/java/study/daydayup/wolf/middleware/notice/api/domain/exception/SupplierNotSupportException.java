package study.daydayup.wolf.middleware.notice.api.domain.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class SupplierNotSupportException extends SystemException {
    public SupplierNotSupportException() {
        super(124000, "Supplier do not support such method" );
    }
}
