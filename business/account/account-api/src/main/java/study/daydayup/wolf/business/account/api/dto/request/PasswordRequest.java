package study.daydayup.wolf.business.account.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.business.account.api.entity.AuthEnv;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.account.auth.agent.dto
 *
 * @author Wingle
 * @since 2019/12/5 10:07 上午
 **/
@Data
public class PasswordRequest implements Request {
    @NotBlank
    private String account;
    private long orgId;

    @NotBlank
    private String password;
    private String newPassword;
    private String repeatedPassword;
    @NotBlank
    private String captcha;

    private String source;
    private String token;
    private String scope;

    private int expiredIn;
    private int refreshExpiredIn;

    private AuthEnv env;
}
