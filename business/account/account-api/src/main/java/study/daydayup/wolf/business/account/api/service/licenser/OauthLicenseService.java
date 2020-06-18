package study.daydayup.wolf.business.account.api.service.licenser;

import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.account.api.service.licenser
 *
 * @author Wingle
 * @since 2019/12/5 4:16 下午
 **/
public interface OauthLicenseService {
    OauthLicense findByAccessToken(String accessToken);
    OauthLicense findByRefreshToken(String refreshToken);

    OauthLicense grant(LicenseRequest request);

    void create(OauthLicense license);
    void refresh(String refreshToken, LocalDateTime expiredAt);
    void expire(String accessToken, LocalDateTime expiredAt);
    void expire(String accessToken);
    void changeScope(String accessToken, String scope);

}
