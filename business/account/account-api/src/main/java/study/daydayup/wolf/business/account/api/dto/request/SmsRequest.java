package study.daydayup.wolf.business.account.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.business.account.api.entity.AuthEnv;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * study.daydayup.wolf.business.account.auth.agent.dto
 *
 * @author Wingle
 * @since 2019/12/5 10:07 上午
 **/
@Data
public class SmsRequest extends Request {
    @NotBlank
    private String mobile;
    @NotBlank
    private String code;

    private long orgId;

    private String token;
    private String scope;
    private String source;

    private long expiredIn;
    private long refreshExpiredIn;

    private AuthEnv env;
}
