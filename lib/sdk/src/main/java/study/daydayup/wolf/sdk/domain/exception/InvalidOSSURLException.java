package study.daydayup.wolf.sdk.domain.exception;

import lombok.NonNull;
import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class InvalidOSSURLException extends SystemException {
    public InvalidOSSURLException() {
       this("Invalid sdk config");
    }

    public InvalidOSSURLException(@NonNull String msg) {
        super(124000, msg);
    }
}
