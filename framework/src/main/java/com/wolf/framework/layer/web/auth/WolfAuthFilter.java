package com.wolf.framework.layer.web.auth;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
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

    }

}
