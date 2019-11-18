package study.daydayup.wolf.demo.account.api.dto.request.auth;

import lombok.Data;

@Data
public class RefreshTokenRequest extends AuthRequest {

    private String accessToken;

    private String refreshToken;
}
