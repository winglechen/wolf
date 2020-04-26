package study.daydayup.wolf.business.account.auth.agent.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class CompanyNotChosenException extends BusinessException {
    public CompanyNotChosenException() {
        super(110501, "Company Not chosen" );
    }
}
