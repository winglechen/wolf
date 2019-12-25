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
    @NotBlank
    private String password;
    private String repeatedPassword;

    private String source;
    private String token;
    private String scope;

    private long orgId;

    private long expiredIn;
    private long refreshExpiredIn;

    private AuthEnv env;
}
