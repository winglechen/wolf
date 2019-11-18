package study.daydayup.wolf.demo.account.biz.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatProperties {

    /**
     * 公众号id.
     **/
    private String mpAppId;

    /**
     * 公众号secret.
     **/
    private String mpSecret;

    /**
     * app配置id
     */
    private String thirdAppAppId;

    /**
     * app配置secret.
     */
    private String thirdAppSecret;

    /**
     * 小程序id.
     **/
    private String miniAppId;

    /**
     * secret.
     **/
    private String miniSecret;

    private String mpAccessTokenUrl;

    private String appAccessTokenUrl;

    private String refreshTokenUrl;

    private String sessionKeyUrl;

    private String userInfoUrl;

}
