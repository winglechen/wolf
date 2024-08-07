package com.wolf.framework.layer.web.auth;

import com.wolf.common.util.collection.CollectionUtil;
import com.wolf.common.util.net.AntPathUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WolfAuthFilter implements Filter {

    private final WolfAuth auth;
    private final AuthConfig config;

    public WolfAuthFilter(WolfAuth auth, AuthConfig config) {
        this.auth = auth;
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        String path = httpRequest.getServletPath();
        if (!isNeedAuth(path)) {
            return;
        }

        if (!auth.isLogin()) {
            accessDeny(httpResponse);
        }

        chain.doFilter(request, response);
    }

    private boolean isNeedAuth(String path) {
        if (isExcludePath(path)) {
            return false;
        }

        return isAuthPath(path);
    }

    private boolean isAuthPath(String path) {
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

    private boolean isExcludePath(String path) {
        for (String pattern : config.getExcludePath()) {
            boolean isMatch = AntPathUtil.match(pattern, path);
            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    private void accessDeny(HttpServletResponse response) throws IOException {
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
