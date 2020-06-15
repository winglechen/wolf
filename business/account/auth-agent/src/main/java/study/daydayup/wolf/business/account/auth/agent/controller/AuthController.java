package study.daydayup.wolf.business.account.auth.agent.controller;

import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.auth.agent.Session;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 * base auth controller : cookie | header | token -> handling
 *
 * @author Wingle
 * @since 2019/12/4 5:47 下午
 **/
public class AuthController {
    @Resource
    private Session session;

    protected void saveLicenseToSession(OauthLicense license) {
        session.saveLicense(license);
    }

    protected OauthLicense getLicenseFromSession() {
        OauthLicense license = new OauthLicense();

        license.setAccessToken(session.get("accessToken", String.class));
        license.setRefreshToken(session.get("refreshToken", String.class));

        Long expiredAt = session.get("expiredAt", Long.class);
        Long refreshExpiredAt = session.get("refreshExpiredAt", Long.class);
        license.setExpiredAt(LocalDateTime.ofEpochSecond(expiredAt, 0, ZoneOffset.UTC));
        license.setRefreshExpiredAt(LocalDateTime.ofEpochSecond(refreshExpiredAt, 0, ZoneOffset.UTC));

        return license;
    }

    protected OauthLicense filterLicense(OauthLicense license) {
        if (license == null) {
            return null;
        }

        license.setAccountId(null);
        license.setAccount(null);
        license.setAccountType(null);
        license.setClientId(null);
        license.setScope(null);

        return license;
    }

    protected boolean isLogin() {
        return session.isLogin();
    }

    protected String formatScope(String scope, Long orgId) {
        if (null == scope && null == orgId) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        if (null != scope) {
            builder.append(scope);
        }
        builder.append(orgId);

        return builder.toString();
    }
}
