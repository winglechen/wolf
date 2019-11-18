package study.daydayup.wolf.demo.account.api.dto.request.auth;

import lombok.Data;
import study.daydayup.wolf.demo.account.api.enums.AuthTypeEnum;
import study.daydayup.wolf.demo.account.api.enums.ClientEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {

    /**
     * @see ClientEnum
     */
    @NotBlank(message = "clientId不能为空")
    private String clientId;

    /**
     * @see AuthTypeEnum
     */
    @NotNull(message = "授权类型不能为空")
    protected Integer authorizationType;

}
