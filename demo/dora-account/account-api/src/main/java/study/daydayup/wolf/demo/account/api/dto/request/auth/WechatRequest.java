package study.daydayup.wolf.demo.account.api.dto.request.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WechatRequest extends AuthRequest {

    @NotBlank(message = "授权码不能为空")
    private String code;
}

