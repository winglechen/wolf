package com.wolf.framework.layer.web.auth;

import com.wolf.common.lang.exception.api.NoPermissionException;
import com.wolf.common.lang.exception.api.NotLoggedInException;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.common.util.net.AntPathUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Auth {
    private final Session session;
    private final AuthConfig config;

    public Auth(Session session, AuthConfig config) {
        this.session = session;
        this.config = config;
    }

    public Long getAccountId() {
        if (!isLogin()) {
            throw new NotLoggedInException();
        }

        return session.get(config.getAccountKey(), Long.class);
    }

    public Long getSpaceId() {
        if (!isLogin()) {
            throw new NoPermissionException();
        }

        return session.get(config.getSpaceKey(), Long.class);
    }

    public boolean isLogin() {
        Long accountId = session.get(config.getAccountKey(), Long.class);
        return null != accountId && accountId > 0;
    }

    public boolean isLogin(Long spaceId) {
        if (!isLogin()) {
            return false;
        }
        Long sessionSpaceId = session.get(config.getSpaceKey(), Long.class);
        return spaceId != null && spaceId.equals(sessionSpaceId);
    }

    public void login(Long accountId) {
        session.set(config.getAccountKey(), accountId);
    }

    public void login(Long spaceId, Long accountId) {
        login(accountId);
        session.set(config.getSpaceKey(), spaceId);
    }

    public void logout() {
        session.set(config.getAccountKey(), null);
        session.set(config.getSpaceKey(), null);
    }

    public void logout(Long spaceId) {
        if (null == spaceId) {
            return;
        }

        Long sessionSpaceId = session.get(config.getSpaceKey(), Long.class);
        if (!spaceId.equals(sessionSpaceId)) {
            return;
        }

        session.set(config.getSpaceKey(), null);
    }

    public boolean isNeedAuth(String path) {
        if (isExcludePath(path)) {
            return false;
        }

        return isAuthPath(path);
    }

    public boolean isAuthPath(String path) {
        if (CollectionUtil.isEmpty(config.getAuthPath())) {
            return true;
        }

        for (String pattern : config.getAuthPath()) {
            boolean isMatch = AntPathUtil.match(pattern, path);
            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    public boolean isExcludePath(String path) {
        for (String pattern : config.getExcludePath()) {
            boolean isMatch = AntPathUtil.match(pattern, path);
            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    public void accessDeny(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        String data = """
            {
                "code" : "%s",
                "message" : "%s",
                "data"  : "",
            }
            """.formatted(config.getDenyCode(), config.getDenyMessage());

        response.getWriter().println(data);
    }


}
