package study.daydayup.wolf.business.uc.api.notice.domain.exception;

import lombok.NonNull;
import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class InvalidSMSConfigException extends SystemException {
    public InvalidSMSConfigException() {
       this("Invalid sms config");
    }

    public InvalidSMSConfigException(@NonNull String msg) {
        super(124000, msg);
    }
}
