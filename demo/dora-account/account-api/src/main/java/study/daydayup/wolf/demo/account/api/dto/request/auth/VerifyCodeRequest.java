package study.daydayup.wolf.demo.account.api.dto.request.auth;

import lombok.Data;

@Data
public class VerifyCodeRequest extends AuthRequest {

    private String mobile;

    private String code;

}
