package study.daydayup.wolf.demo.account.api.dto.response.license;

import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import lombok.Data;

@Data
public class OAuth2ResponseDTO extends LicenseDTO {

    private String accessToken;

    private String refreshToken;

    private Long nowTime;

    private Long accessTokenExpire;

    private Long refreshTokenExpire;
}
