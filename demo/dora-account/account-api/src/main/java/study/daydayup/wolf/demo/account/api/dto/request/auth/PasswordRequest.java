package study.daydayup.wolf.demo.account.api.dto.request.auth;

import lombok.Data;

@Data
public class PasswordRequest extends AuthRequest {

    private String mobile;

    private String password;
}
