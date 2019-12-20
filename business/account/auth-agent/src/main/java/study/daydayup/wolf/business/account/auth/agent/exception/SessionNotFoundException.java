package study.daydayup.wolf.business.account.auth.agent.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;
import study.daydayup.wolf.common.lang.string.Msg;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class SessionNotFoundException extends SystemException {
    public SessionNotFoundException(String field) {
        super(111000, Msg.join("Couldn't find field:", field, " in session") );
    }
}
