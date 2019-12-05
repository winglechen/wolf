package study.daydayup.wolf.business.account.auth.agent;

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
public class SessionID {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String sessionKey;

    @Resource
    private AuthConfig config;

    SessionID(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.sessionKey = config.getSessionKey();
    }

    public static String init(HttpServletRequest request, HttpServletResponse response) {
        SessionID sessionID = new SessionID(request, response);

        return sessionID.build();
    }

    public String build() {
        String sId;

        sId = CookieUtil.get(request, sessionKey);
        if (null != sId) {
            return sId;
        }

        sId = request.getHeader(sessionKey);
        if (null != sId) {
            return sId;
        }

        sId = create();
        CookieUtil.set(response, sessionKey, sId, true);
        return sId;
    }

    private String create() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
