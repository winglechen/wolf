package study.daydayup.wolf.business.account.api.exception;

import study.daydayup.wolf.common.lang.exception.BusinessException;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.account.auth.agent.exception
 *
 * @author Wingle
 * @since 2019/12/12 3:04 下午
 **/
public class InvalidVerifyCodeException extends BusinessException {
    public InvalidVerifyCodeException() {
        super(111005, "Invalid OTP code, Pls try again");
    }

    public InvalidVerifyCodeException(String phoneNum) {
        super(111005, StringUtil.join("Invalid verify code for: ", phoneNum));
    }
}
