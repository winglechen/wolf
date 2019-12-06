package study.daydayup.wolf.business.account.api.dto.request;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * study.daydayup.wolf.business.account.api.dto.request
 *
 * @author Wingle
 * @since 2019/12/6 3:54 下午
 **/
@Data
public class LicenseRequest extends Request {
    @NotNull @Min(1)
    private long accountId;

    private String clientId;
    private String scope;

    private String token;

    @NotNull @Min(10)
    private int expiredIn;
    @NotNull @Min(10)
    private int refreshExpiredIn;

}
