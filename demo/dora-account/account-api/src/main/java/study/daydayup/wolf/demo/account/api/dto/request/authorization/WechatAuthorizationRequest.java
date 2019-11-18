package study.daydayup.wolf.demo.account.api.dto.request.authorization;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WechatAuthorizationRequest extends AuthorizationRequest {

    @NotBlank(message = "授权码不能为空")
    private String code;
}

