package study.daydayup.wolf.demo.account.api.dto.request.license;

import lombok.Data;
import study.daydayup.wolf.demo.account.api.enums.ClientEnum;
import study.daydayup.wolf.demo.account.api.enums.GrantTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public abstract class LicenseRequest {

    /**
     * @see ClientEnum
     */
    @NotBlank(message = "clientId不能为空")
    private String clientId;

    /**
     * @see GrantTypeEnum
     */
    @NotNull(message = "授权类型不能为空")
    private Integer grantType;

}
