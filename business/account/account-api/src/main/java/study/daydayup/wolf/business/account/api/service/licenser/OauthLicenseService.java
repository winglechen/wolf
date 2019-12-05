package study.daydayup.wolf.business.account.api.service.licenser;

import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;

import java.util.Date;

/**
 * study.daydayup.wolf.business.account.api.service.licenser
 *
 * @author Wingle
 * @since 2019/12/5 4:16 下午
 **/
public interface OauthLicenseService {
    OauthLicense findByAccessToken(String accessToken);
    OauthLicense findByRefreshToken(String refreshToken);

    void createLicense(OauthLicense license);
    void refresh(String refreshToken, Date expiredAt);
}
