package study.daydayup.wolf.framework.security.filter.http.detector;

import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.framework.security.filter.http.HttpRequestFilterConfig;
import study.daydayup.wolf.framework.security.filter.http.wrapper.RequestWrapper;

import java.util.List;
import java.util.Map;

/**
 * @author weixing
 * @since 2022/11/15 19:36
 */
@Slf4j
public class XssDetector extends AbstractDetector implements Detector {

    public XssDetector(HttpRequestFilterConfig httpRequestFilterConfig) {
        this.httpRequestFilterConfig = httpRequestFilterConfig;
    }

    @Override
    public String getType() {
        return "xss or html character";
    }

    @Override
    public String getMessage() {
        return "Illegal character detected";
    }

    @Override
    public boolean detect(RequestWrapper request) {
        return super.detect(request);
    }

    @Override
    public boolean shouldDetect() {
        return this.httpRequestFilterConfig.getDisabledDetectors() == null
            || !this.httpRequestFilterConfig.getDisabledDetectors().contains("XssDetector");
    }

    public boolean detectInputValue(String input) {
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            switch (character) {
                case '<':
                case '>':
                    return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, List<String>> getCheckWhitelist() {
        return httpRequestFilterConfig.getHtmlCheckWhitelist();
    }
}
