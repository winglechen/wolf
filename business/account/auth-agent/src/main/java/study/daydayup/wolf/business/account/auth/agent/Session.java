package study.daydayup.wolf.business.account.auth.agent;

import lombok.NonNull;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;
import study.daydayup.wolf.business.account.auth.agent.exception.CompanyNotChosenException;
import study.daydayup.wolf.business.account.auth.agent.exception.SessionNotFoundException;
import study.daydayup.wolf.business.account.auth.agent.util.CookieUtil;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * study.daydayup.wolf.business.account.auth.agent
 * TODO: store it to redis
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
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private AuthConfig config;
    @Reference
    private OauthLicenseService oauthLicenseService;

    public void init(HttpServletRequest request, HttpServletResponse response) {
        if (null != data) {
            return;
        }
        data = new HashMap<>();

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
        set(key, value, true);
    }

    public void set(@NonNull String key, Object value, boolean saveToRedis) {
        data.put(key, value);

        if (saveToRedis) {
            redisSet(key, value);
        }
    }

    public <T> T get(String key, Class<T> type) {
       return get(key, type, true);
    }

    public <T> T get(String key, Class<T> type, boolean throwException) {
        Object value = get(key);
        if (value == null) {
            if (!throwException) {
                return null;
            }
            if ("orgId".equals(key)) {
                throw new CompanyNotChosenException();
            }
            throw new SessionNotFoundException(key);
        }

        if(type.equals(value.getClass())) {
            return type.cast(value);
        }

        return null;
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void destroy() {
        Date now = new Date();
        if ( !isLogin(now) ) {
            return;
        }

        set("expiredAt", now.getTime());
        oauthLicenseService.expire(sessionId, now);
    }

    // account & orgId operations
    public boolean isLogin(Date now) {
        if(null == get("accountId")) {
            return false;
        }

        if (null == now) {
            now = new Date();
        }

        Long expiredAt = get("expiredAt", Long.class);
        return expiredAt > now.getTime();
    }

    public boolean isLogin() {
        return isLogin(new Date());
    }



    public void changeScope(@NonNull Long orgId) {
        set("orgId", orgId);
        oauthLicenseService.changeScope(sessionId, String.valueOf(orgId));
    }

    private Long parseOrgId(String scope) {
        if (StringUtil.isBlank(scope)) {
            return null;
        }

        try {
            return Long.valueOf(scope.trim());
        } catch (Exception ignored) {
            return null;
        }
    }

    // sessionId operations
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

        sId = getBearer();
        if (null != sId) {
            return sId;
        }

        sId = request.getHeader(sessionKey);
        if (null != sId) {
            return sId;
        }

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String getBearer() {
        String auth = request.getHeader("Authorization");
        if (StringUtil.isEmpty(auth)) {
            return null;
        }

        String[] authArr = auth.split(" ");
        if (auth.length() != 2 || !"Bearer".equals(authArr[0])) {
            return null;
        }

        if (StringUtil.isEmpty(authArr[1])) {
            return null;
        }

        return authArr[1].trim();
    }

    //redis operations
    private void redisSet(String key, Object value) {
        getRedisHashOps().put(sessionId, key, value);
    }

    public void storeToRedis() {
        if (MapUtil.isEmpty(data)) {
            return;
        }

        //TODO: add batch update when request finished
        getRedisHashOps().putAll(sessionId, data);
    }

    private HashOperations<String, String, Object> getRedisHashOps() {
        return redisTemplate.opsForHash();
    }

    private void loadFromRedis() {
        Map<String, Object> tmpData = getRedisHashOps().entries(sessionId);
        if (MapUtil.notEmpty(tmpData)) {
            saveRedisData(tmpData);
            return;
        }

        loadFromRpc();
        storeToRedis();
    }

    private void loadFromRpc() {
        OauthLicense license = oauthLicenseService.findByAccessToken(sessionId);
        if (license == null) {
            return;
        }

        saveLicense(license);
    }

    private void saveRedisData(Map<String, Object> cache) {
        if (MapUtil.isEmpty(cache)) {
            return;
        }

        if (null != cache.get("accountId")) data.put("accountId", (Long)cache.get("accountId"));
        if (null != cache.get("orgId")) data.put("orgId", (Long)cache.get("orgId"));
        if (null != cache.get("account")) data.put("account", cache.get("account"));
        if (null != cache.get("accountType")) data.put("accountType", (Integer)cache.get("accountType"));
        if (null != cache.get("scope")) data.put("scope", cache.get("scope"));
        if (null != cache.get("accessToken")) data.put("accessToken", cache.get("accessToken"));
        if (null != cache.get("refreshToken")) data.put("refreshToken", cache.get("refreshToken"));
        if (null != cache.get("expiredAt")) data.put("expiredAt", (Long)cache.get("expiredAt"));
        if (null != cache.get("refreshExpiredAt")) data.put("refreshExpiredAt", (Long)cache.get("refreshExpiredAt"));
    }

    public void saveLicense(OauthLicense license) {
        if (null == license) {
            return;
        }

        set("accountId", license.getAccountId(), false);
        set("account", license.getAccount(), false);
        set("accountType", license.getAccountType(), false);
        set("accessToken", license.getAccessToken(), false);
        set("refreshToken", license.getRefreshToken(), false);
        set("scope", license.getScope(), false);
        set("expiredAt", license.getExpiredAt().getTime(), false);
        set("refreshExpiredAt", license.getRefreshExpiredAt().getTime(), false);

        Long orgId = parseOrgId(license.getScope());
        if (orgId != null) {
            set("orgId", orgId, false);
        }

        if (!sessionId.equals(license.getAccessToken())) {
            setSessionId(license.getAccessToken());
        }

        storeToRedis();
    }

}
