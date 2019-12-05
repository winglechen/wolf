package study.daydayup.wolf.business.account.biz.api;

import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;

import java.util.Date;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 7:20 下午
 **/
public class OauthLicenseServiceImpl implements OauthLicenseService {
    @Override
    public OauthLicense findByAccessToken(String accessToken) {
        return null;
    }

    @Override
    public OauthLicense findByRefreshToken(String refreshToken) {
        return null;
    }

    @Override
    public void createLicense(OauthLicense license) {

    }

    @Override
    public void refresh(String refreshToken, Date expiredAt) {

    }
}
