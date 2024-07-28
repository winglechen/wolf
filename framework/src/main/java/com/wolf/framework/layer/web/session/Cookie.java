package com.wolf.framework.layer.web.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Cookie {
    // 最大缓存时间,单位/秒, 2H
    private static final int COOKIE_MAX_AGE = 60 * 60 * 2;
    // 默认缓存时间,单位/秒, 30分钟
    private static final int COOKIE_DEFAULT_AGE = 60 * 30;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";

    private final HttpServletRequest servletRequest;
    private final HttpServletResponse servletResponse;

    public Cookie(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    public void set(String key, String value, boolean isSecure) {
        set(key, value, null, COOKIE_PATH, COOKIE_DEFAULT_AGE, true, isSecure);
    }


    public void set(String key, String value) {
        set(key, value, null, COOKIE_PATH, COOKIE_DEFAULT_AGE, true, false);
    }

    private void set(String key, String value, String domain, String path, int maxAge, boolean isHttpOnly, boolean isSecure) {
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(isSecure);
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
            set(key, "", null, COOKIE_PATH, 0, true, false);
        }
    }

}
