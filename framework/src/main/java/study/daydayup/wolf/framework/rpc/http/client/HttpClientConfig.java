package study.daydayup.wolf.framework.rpc.http.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.framework.layer.api.Config;
import study.daydayup.wolf.framework.rpc.http.client.cookie.CookiePolicyEnum;

import javax.annotation.PostConstruct;

/**
 * study.daydayup.wolf.framework.util.http
 *
 * @author Wingle
 * @since 2021/2/4 2:30 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.httpclient", ignoreInvalidFields = true)
public class HttpClientConfig implements Config {
    private Integer cookiePolicy;
    private String cookieJar;
    private boolean enableCookieManagement;

    private String keyStore;
    private String keyStorePassword;

    public CookiePolicyEnum getCookiePolicy() {
        if (null == cookiePolicy) {
            return CookiePolicyEnum.ACCEPT_NONE;
        }

        return EnumUtil.codeOf(cookiePolicy, CookiePolicyEnum.class);
    }

    @PostConstruct
    public void bootstrap() {
        HttpClient.init(this);
    }
}
