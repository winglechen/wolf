package com.onedot.win.framework.security.filter.http.detector;

import lombok.extern.slf4j.Slf4j;
import com.onedot.win.framework.security.filter.http.HttpRequestFilterConfig;
import com.onedot.win.framework.security.filter.http.wrapper.RequestWrapper;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author weixing
 * @since 2022/11/15 19:36
 */
@Slf4j
public class SqlInjectionDetector extends AbstractDetector implements Detector {

    private static final String SQL_REGEXP = "\\b(select(\\s)+|update(\\s)+|delete(\\s)+|insert(\\s)+((ignore)*|(\\s)+)+into(\\s)+|exec(\\s)+|trancate(\\s)+|drop(\\s)+|execute(\\s)+|waitfor(\\s)+)";
    private static final Pattern sqlPattern = Pattern.compile(SQL_REGEXP, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    public SqlInjectionDetector(HttpRequestFilterConfig httpRequestFilterConfig) {
        this.httpRequestFilterConfig = httpRequestFilterConfig;
    }

    @Override
    public String getType() {
        return "sql injection";
    }

    @Override
    public String getMessage() {
        return "Illegal request detected";
    }

    @Override
    public boolean detect(RequestWrapper request) {
        return super.detect(request);
    }

    @Override
    public boolean shouldDetect() {
        return this.httpRequestFilterConfig.getDisabledDetectors() == null
            || !this.httpRequestFilterConfig.getDisabledDetectors().contains("SqlInjectionDetector");
    }

    public boolean detectInputValue(String input) {
        return sqlPattern.matcher(input).find();
    }

    @Override
    public Map<String, List<String>> getCheckWhitelist() {
        return httpRequestFilterConfig.getSqlInjectionWhitelist();
    }
}
