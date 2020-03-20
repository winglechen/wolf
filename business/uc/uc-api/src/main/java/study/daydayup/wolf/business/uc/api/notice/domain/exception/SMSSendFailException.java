package study.daydayup.wolf.business.uc.api.notice.domain.exception;

import lombok.NonNull;
import study.daydayup.wolf.common.lang.exception.BusinessException;
import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class SMSSendFailException extends BusinessException {
    public SMSSendFailException() {
       this("Sms send fail");
    }

    public SMSSendFailException(@NonNull String msg) {
        super(124000, msg);
    }
}
