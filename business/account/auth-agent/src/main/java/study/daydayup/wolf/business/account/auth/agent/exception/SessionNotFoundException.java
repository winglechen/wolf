package study.daydayup.wolf.business.account.auth.agent.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;
import study.daydayup.wolf.common.lang.string.Str;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class SessionNotFoundException extends BusinessException {
    public SessionNotFoundException(String field) {
        super(110000, Str.join("Couldn't find field:", field, " in session") );
    }
}
