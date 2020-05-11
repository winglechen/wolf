package study.daydayup.wolf.middleware.notice.api.domain.exception;

import lombok.NonNull;
import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class SMSConfigNotFoundException extends SystemException {
    public SMSConfigNotFoundException() {
       this("sms config not found");
    }

    public SMSConfigNotFoundException(@NonNull String msg) {
        super(124000, msg);
    }
}
