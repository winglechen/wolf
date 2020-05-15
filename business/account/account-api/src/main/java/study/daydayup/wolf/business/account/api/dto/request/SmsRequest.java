package study.daydayup.wolf.business.account.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.business.account.api.entity.AuthEnv;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.account.auth.agent.dto
 *
 * @author Wingle
 * @since 2019/12/5 10:07 上午
 **/
@Data
public class SmsRequest implements Request {
    @NotBlank(message = "Mobile can not be null")
    private String mobile;
    @NotBlank(message = "OTP can not be null")
    private String code;

    private Long orgId;

    private String source;
    private String token;
    private String scope;

    private int expiredIn;
    private int refreshExpiredIn;

    private AuthEnv env;
}
