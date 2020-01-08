package study.daydayup.wolf.business.account.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.business.account.api.entity.AuthEnv;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * study.daydayup.wolf.business.account.api.dto.request
 *
 * @author Wingle
 * @since 2019/11/21 9:56 下午
 **/
@Data
public class SmsCodeRequest implements Request {
    @NotBlank
    private String mobile;
    private Long orgId;

    private Integer expiredIn;
    private Integer numCount;

    private AuthEnv env;
}
