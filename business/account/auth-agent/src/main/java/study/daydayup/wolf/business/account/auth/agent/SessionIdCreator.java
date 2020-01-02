package study.daydayup.wolf.business.account.auth.agent;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;
import study.daydayup.wolf.business.account.auth.agent.util.CookieUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * study.daydayup.wolf.business.account.auth.agent
 *
 * @author Wingle
 * @since 2019/12/5 12:10 下午
 **/
public class SessionIdCreator {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String sessionKey;

    private AuthConfig config;

    SessionIdCreator(HttpServletRequest request, HttpServletResponse response, AuthConfig config) {
        this.request = request;
        this.response = response;
        this.config = config;
        this.sessionKey = config.getSessionKey();
    }

    public String getExistedId() {
        String sId;

        sId = CookieUtil.get(request, sessionKey);
        if (null != sId) {
            return sId;
        }

        sId = request.getHeader(sessionKey);
        if (null != sId) {
            return sId;
        }

        return null;
    }

    public String create() {
        String sId = UUID.randomUUID().toString().replaceAll("-", "");
        CookieUtil.set(response, sessionKey, sId, true);

        return sId;
    }

    public void changeId(String sessionId) {
        if (sessionId == null) {
            return;
        }
        CookieUtil.set(response, sessionKey, sessionId, true);
    }

}
