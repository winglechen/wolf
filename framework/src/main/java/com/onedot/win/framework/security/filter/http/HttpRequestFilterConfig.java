package com.onedot.win.framework.security.filter.http;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.onedot.win.common.util.collection.ListUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weixing
 * @since 2022/11/18 11:44
 */
@Configuration
public class HttpRequestFilterConfig {
    @Getter
    @Value("${wolf.server.http.filter.allowedMethod.global:GET,POST,PUT,DELETE}")
    private List<String> allowedRequestMethod;

    /**
     * @todolist
     * /user/login/*, -PUT,HEAD
     */
    @Value("#{${wolf.server.http.filter.allowedMethod.urlMapping:null}}")
    private List<String> urlMethodMapping;

    @Getter
    @Value("${wolf.server.http.filter.disabledDetectors:}")
    private List<String> disabledDetectors;

    @NacosValue(value = "${wolf.server.http.filter.htmlCheckWhitelist:}", autoRefreshed = true)
    //@Value("${wolf.server.http.filter.htmlCheckWhitelist:null}")
    private List<String> htmlCheckWhitelist;

    @NacosValue(value = "${wolf.server.http.filter.sqlInjectionWhitelist:}", autoRefreshed = true)
    //@Value("${wolf.server.http.filter.sqlInjectionWhitelist:null}")
    private List<String> sqlInjectionWhitelist;

    public Map<String, List<String>> getHtmlCheckWhitelist() {
        return getParsedWhitelist(htmlCheckWhitelist);
    }

    public Map<String, List<String>> getSqlInjectionWhitelist() {
        return getParsedWhitelist(sqlInjectionWhitelist);
    }

    /**
     * @param checkWhitelist List<String>
     * @return Map<String, List<String>>
     */
    private Map<String/*URI*/, List<String/*Parameter*/>> getParsedWhitelist(List<String> checkWhitelist) {
        Map<String, List<String>> parsedCheckWhitelist = new HashMap<>();
        if (ListUtil.isEmpty(checkWhitelist)) {
            return parsedCheckWhitelist;
        }

        for (String whitelist : checkWhitelist) {
            if (!whitelist.contains(":")) {
                return parsedCheckWhitelist;
            }

            String[] item = whitelist.split(":");
            List<String> parameters = Arrays.asList(item[1].split("/"));
            parsedCheckWhitelist.put(item[0], parameters);
        }

        return parsedCheckWhitelist;
    }
}
