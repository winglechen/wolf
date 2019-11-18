package study.daydayup.wolf.demo.account.api.dto.request.authorization;

import lombok.Data;

@Data
public class RefreshTokenAuthorizationRequest extends AuthorizationRequest {

    private String accessToken;

    private String refreshToken;
}
