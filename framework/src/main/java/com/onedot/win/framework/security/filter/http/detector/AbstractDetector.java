package com.onedot.win.framework.security.filter.http.detector;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import com.onedot.win.common.util.collection.ListUtil;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.JSONUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.security.filter.http.HttpRequestFilterConfig;
import com.onedot.win.framework.security.filter.http.wrapper.RequestWrapper;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author weixing
 * @since 2022/11/18 11:18
 */
@Slf4j
public abstract class AbstractDetector {

    protected HttpRequestFilterConfig httpRequestFilterConfig;

    protected boolean detect(RequestWrapper request) {
        if (!shouldDetect()) {
            return false;
        }

        // detect request querystring or request form data
        String requestURI = request.getRequestURI();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] values = request.getParameterValues(parameterName);

            for (String value : values) {
                if (hitURIAndParameterWhitelist(requestURI, parameterName)) {
                    log.info("hitURIAndParameterWhitelist, ignore check. URI={}, parameter={}", requestURI, parameterName);
                    return false;
                }
                if (detectInputValue(value)) {
                    return true;
                }
            }
        }

        // detect request body
        String requestBody = request.getRequestBodyString();
        if (StringUtil.notBlank(requestBody)
            && null != request.getContentType()
            && request.getContentType().toLowerCase().contains("application/json")
            && requestBody.startsWith("{") && requestBody.endsWith("}")) {

            if (!hitURIWhitelist(requestURI)) {
                return detectInputValue(requestBody);
            }

            JSONObject json = JSONUtil.parse(requestBody);

            for (Map.Entry<String, Object> entry : json.getInnerMap().entrySet()) {
                // only check string type
                if (!(entry.getValue() instanceof String)) {
                    return false;
                }
                String parameterValue = (String) entry.getValue();

                if (hitURIAndParameterWhitelist(requestURI, entry.getKey())) {
                    log.info("hitURIAndParameterWhitelist, ignore check. URI={}, parameter={}", requestURI, entry.getKey());
                    return false;
                }

                if (detectInputValue(parameterValue)) {
                    return true;
                }
            }
        }

        return false;
    }

    public abstract boolean shouldDetect();

    public abstract Map<String, List<String>> getCheckWhitelist();

    public abstract boolean detectInputValue(String input);

    protected boolean hitURIWhitelist(String requestURI) {
        if (MapUtil.isEmpty(getCheckWhitelist())) {
            return false;
        }

        if (getCheckWhitelist().containsKey(requestURI)) {
            return true;
        }

        return false;
    }

    protected boolean hitURIAndParameterWhitelist(String requestURI, String parameterName) {
        if (!hitURIWhitelist(requestURI)) {
            return false;
        }

        List<String> parameterNames = getCheckWhitelist().get(requestURI);
        if (ListUtil.isEmpty(parameterNames)) {
            return false;
        }

        if (parameterNames.contains(parameterName)) {
            log.info("page={} parameter={} in whitelist, ignore detect.", requestURI, parameterName);
            return true;
        }

        return false;
    }
}
