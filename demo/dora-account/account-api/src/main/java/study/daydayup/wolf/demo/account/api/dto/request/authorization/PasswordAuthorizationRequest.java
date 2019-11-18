package study.daydayup.wolf.demo.account.api.dto.request.authorization;

import lombok.Data;

@Data
public class PasswordAuthorizationRequest extends AuthorizationRequest {

    private String mobile;

    private String password;
}
