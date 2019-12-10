package study.daydayup.wolf.business.account.auth.agent;

import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.account.api.entity.License;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.account.auth.agent
 * facade
 * @author Wingle
 * @since 2019/12/4 5:50 下午
 **/
public class Session {
    private String sessionID;
    private Map<String, Object> data;

    @Resource
    private AuthConfig config;
    @Reference
    private OauthLicenseService oauthLicenseService;

    public void init(HttpServletRequest request, HttpServletResponse response) {
        if (null != data) {
            return;
        }
        data = new HashMap<String, Object>();

        SessionIDCreator sessionIDCreator = new SessionIDCreator(request, response, config);
        String token = sessionIDCreator.getExistedID();
        if(null != token) {
            sessionID = token;
        } else {
            sessionID = sessionIDCreator.create();
        }

        loadFromRedis();
    }

    public String getSessionID() {
        return sessionID;
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public boolean isLogin(Date now) {
        if(null == get("accountId")) {
            return false;
        }

        if (null == now) {
            now = new Date();
        }

        Date expiredAt = (Date)get("expiredAt");
        if (expiredAt.before(now)) {
            return false;
        }

        return true;
    }

    public boolean isLogin() {
        return isLogin(new Date());
    }

    public void destroy() {
        Date now = new Date();
        if ( !isLogin(now) ) {
            return;
        }

        set("expiredAt", now);
        oauthLicenseService.expire(sessionID, now);
    }

    @PreDestroy
    public void storeToRedis() {

    }

    private void loadFromRedis() {
        //TODO

        loadFromRpc();
    }

    private void loadFromRpc() {
        OauthLicense license = oauthLicenseService.findByAccessToken(sessionID);
        saveLicense(license);
    }

    public void saveLicense(OauthLicense license) {
        if (null == license) {
            return;
        }

        set("accountId", license.getAccountId());
        set("accessToken", license.getAccessToken());
        set("refreshToken", license.getRefreshToken());
        set("expiredAt", license.getExpiredAt());
        set("refreshExpiredAt", license.getRefreshExpiredAt());

        String scope = license.getScope().trim();
        Long orgId = Long.valueOf(scope);
        set("orgId", orgId);
    }

}
