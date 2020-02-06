package study.daydayup.wolf.business.uc.api.setting.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class StatusNotFoundException extends SystemException {
    public StatusNotFoundException() {
        super(121000, "Can't find Status" );
    }
}
