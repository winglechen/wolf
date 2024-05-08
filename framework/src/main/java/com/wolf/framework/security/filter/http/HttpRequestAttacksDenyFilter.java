package com.wolf.framework.security.filter.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import com.wolf.common.util.lang.JSONUtil;
import com.wolf.framework.exception.InvalidRequestException;
import com.wolf.framework.security.filter.http.detector.Detector;
import com.wolf.framework.security.filter.http.detector.SqlInjectionDetector;
import com.wolf.framework.security.filter.http.detector.XssDetector;
import com.wolf.framework.security.filter.http.wrapper.RequestWrapper;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weixing
 * @since 2022/11/14 12:00
 */
@Order(-2147483647)
@Slf4j
@Component
public class HttpRequestAttacksDenyFilter implements Filter, InitializingBean {

    private List<Detector> detectors = new ArrayList<>();

    @Resource
    private HttpRequestFilterConfig httpRequestFilterConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        detectors.add(new XssDetector(httpRequestFilterConfig));
        detectors.add(new SqlInjectionDetector(httpRequestFilterConfig));
    }

    @Resource
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RequestWrapper requestWrapper = new RequestWrapper(request);

        for (Detector detector : detectors) {
            if (detector.detect(requestWrapper)) {
                logDetected(requestWrapper, detector.getType());
                handleDetection(requestWrapper, response, detector.getMessage());
                return;
            }
        }

        chain.doFilter(requestWrapper, servletResponse);
    }

    private void logDetected(RequestWrapper request, String detectType) {
        log.warn("{} detected. request page={}, method={}, content-type={}, request params={}, request body={}",
            detectType,
            request.getRequestURL(),
            request.getMethod(),
            request.getContentType(),
            JSONUtil.toJSONString(request.getParameterMap()),
            JSONUtil.toJSONString(request.getRequestBodyString())
        );
    }

    private void handleDetection(HttpServletRequest request, HttpServletResponse response, String message) {
        // @see com.wolf.framework.exception.handler.GlobalExceptionHandler
        handlerExceptionResolver.resolveException(request, response, null, new InvalidRequestException(message));
    }
}
