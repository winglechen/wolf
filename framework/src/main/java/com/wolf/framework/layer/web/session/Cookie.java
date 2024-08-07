package com.wolf.framework.layer.web.session;

import com.wolf.common.util.lang.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Cookie {
    private final HttpServletRequest servletRequest;
    private final HttpServletResponse servletResponse;
    private final SessionConfig sessionConfig;

    public Cookie(HttpServletRequest servletRequest, HttpServletResponse servletResponse, SessionConfig sessionConfig) {
        this.sessionConfig = sessionConfig;
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    public void set(String key, String value) {
        set(key, value,false);
    }

    public void set(String key, String value, boolean isSecure) {
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie(key, value);
        cookie.setSecure(isSecure);

        if (StringUtil.isNoneBlank(sessionConfig.getDomain())) {
            cookie.setDomain(sessionConfig.getDomain());
        }
        cookie.setPath(sessionConfig.getPath());
        cookie.setMaxAge(sessionConfig.getCookieMaxAge());
        cookie.setHttpOnly(sessionConfig.isHttpOnly());

        this.servletResponse.addCookie(cookie);
    }

    public String get(String key) {
        jakarta.servlet.http.Cookie cookie = getCookie(key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    private jakarta.servlet.http.Cookie getCookie(String key) {
        jakarta.servlet.http.Cookie[] arrCookie = this.servletRequest.getCookies();
        if (arrCookie == null) {
            return null;
        }

        for (jakarta.servlet.http.Cookie cookie : arrCookie) {
            if (cookie.getName().equals(key)) {
                return cookie;
            }
        }
        return null;
    }


    public void remove(String key) {
        jakarta.servlet.http.Cookie cookie = getCookie(key);
        if (cookie != null) {
            set(key, "", false);
        }
    }

}
