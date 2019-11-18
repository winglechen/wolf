package study.daydayup.wolf.demo.account.api.dto.request.license;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OAuth2LicenseRequest extends LicenseRequest {

    @NotBlank(message = "授权令牌不能为空")
    private String accessToken;

}
