package study.daydayup.wolf.business.account.auth.agent;

import javax.annotation.PreDestroy;
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

    public void init(HttpServletRequest request, HttpServletResponse response) {
        if (null != data) {
            return;
        }
        data = new HashMap<String, Object>();

        SessionIDCreator sessionIDCreator = new SessionIDCreator(request, response);
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

    }

    public Object get(String key) {
        return data.get(key);
    }

    public void destroy() {

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
    }

}
