package study.daydayup.wolf.demo.account.api.dto.request.authorization;

import lombok.Data;
import study.daydayup.wolf.demo.account.api.enums.AuthorizationTypeEnum;
import study.daydayup.wolf.demo.account.api.enums.ClientEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthorizationRequest {

    /**
     * @see ClientEnum
     */
    @NotBlank(message = "clientId不能为空")
    private String clientId;

    /**
     * @see AuthorizationTypeEnum
     */
    @NotNull(message = "授权类型不能为空")
    protected Integer authorizationType;

}
