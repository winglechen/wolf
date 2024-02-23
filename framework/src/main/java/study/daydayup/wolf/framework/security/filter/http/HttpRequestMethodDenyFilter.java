package study.daydayup.wolf.framework.security.filter.http;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import study.daydayup.wolf.framework.exception.InvalidRequestException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author weixing
 * @since 2022/11/10 15:38
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@Component
public class HttpRequestMethodDenyFilter implements Filter {

    @Resource
    private HttpRequestFilterConfig httpRequestFilterConfig;

    @Resource
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (checkDeniedMethod(request)) {
            log.info("Invalid request detected. request method={}, request:{}", request.getMethod(), request);
            // @see study.daydayup.wolf.framework.exception.handler.GlobalExceptionHandler
            handlerExceptionResolver.resolveException(
                request,
                response,
                null,
                new InvalidRequestException("Request Method '" + request.getMethod() + "' not supported")
            );
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkDeniedMethod(HttpServletRequest request) {
        List<String> methods = getAllowedRequestMethod();
        return !methods.contains(request.getMethod());
    }

    private List<String> getAllowedRequestMethod() {
        // todolist merge urlMethodMapping
        return httpRequestFilterConfig.getAllowedRequestMethod();
    }
}
