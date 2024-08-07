package com.wolf.framework.layer.web.auth;

import com.alibaba.fastjson2.JSON;
import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.framework.layer.web.session.Session;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class WolfAuth {
    private final Session session;
    private final AuthConfig config;

    private List<Space> spaceList;

    public WolfAuth(Session session, AuthConfig config, HttpServletResponse servletResponse) {
        this.session = session;
        this.config = config;
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

    public void setSpaceList(List<Space> spaces) {
        if (CollectionUtil.isEmpty(spaces)) {
            return;
        }
        this.spaceList = spaces;
        session.set(config.getSpaceListKey(), JSON.toJSONString(spaces));
    }

    public List<Space> getSpaceList() {
        if (!CollectionUtil.isEmpty(spaceList)) {
            return spaceList;
        }

        String spaceListJson = session.get(config.getSpaceListKey(), String.class);
        this.spaceList = JSON.parseArray(spaceListJson, Space.class);

        return this.spaceList;
    }
}
