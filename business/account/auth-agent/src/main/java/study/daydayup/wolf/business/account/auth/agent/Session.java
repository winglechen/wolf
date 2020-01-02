package study.daydayup.wolf.business.account.auth.agent;

import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;
import study.daydayup.wolf.business.account.auth.agent.util.CookieUtil;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * study.daydayup.wolf.business.account.auth.agent
 * TODO: move it to the framework package
 * @author Wingle
 * @since 2019/12/4 5:50 下午
 **/
public class Session {
    private String sessionId;
    private String sessionKey;
    private Map<String, Object> data;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Resource
    private AuthConfig config;
    @Reference
    private OauthLicenseService oauthLicenseService;

    public void init(HttpServletRequest request, HttpServletResponse response) {
        if (null != data) {
            return;
        }
        data = new HashMap<String, Object>();

        this.request = request;
        this.response = response;
        this.sessionKey = config.getSessionKey();

        initSessionId();
        loadFromRedis();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        if (sessionId == null) {
            return;
        }

        if (this.sessionId.equals(sessionId)) {
            return;
        }

        this.sessionId = sessionId;
        saveSessionId();
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
        oauthLicenseService.expire(sessionId, now);
    }

    @PreDestroy
    public void storeToRedis() {

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

        if (!sessionId.equals(license.getAccessToken())) {
            setSessionId(license.getAccessToken());
        }
    }


    private void initSessionId() {
        sessionId = createSessionId();
        saveSessionId();
    }

    private void saveSessionId() {
        CookieUtil.set(response, sessionKey, sessionId, true);
    }

    private String createSessionId() {
        String sId;

        sId = CookieUtil.get(request, sessionKey);
        if (null != sId) {
            return sId;
        }

        sId = request.getHeader(sessionKey);
        if (null != sId) {
            return sId;
        }

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private void loadFromRedis() {
        //TODO

        loadFromRpc();
    }

    private void loadFromRpc() {
        OauthLicense license = oauthLicenseService.findByAccessToken(sessionId);
        saveLicense(license);
    }

}
