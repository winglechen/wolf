package study.daydayup.wolf.business.account.auth.agent;

import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            return ;
        }

        sessionID = sessionIDCreator.create();
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

    public void destroy() {
        data.clear();
        oauthLicenseService.expire(sessionID);
    }

    @PreDestroy
    public void storeToRedis() {

    }

    private void loadFromRedis() {
        //TODO

        loadFromRpc();
    }

    private void loadFromRpc() {
        //TODO
        OauthLicense license = oauthLicenseService.findByAccessToken(sessionID);
        if (null == license) {
            return;
        }

    }

}
