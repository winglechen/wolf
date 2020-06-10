package study.daydayup.wolf.business.pay.api.config;

import lombok.Data;

/**
 * study.daydayup.wolf.business.pay.api.config
 *
 * @author Wingle
 * @since 2020/4/26 10:54 下午
 **/
@Data
public class PaySupplier {
    private boolean enable = true;
    private int order = 9999;


    private String appId;
    private String appSecret;

    private String version;
    private String country;
    private String currency;

    private String returnUrl;
    private String notifyUrl;

    private String createUrl;

}
