package study.daydayup.wolf.middleware.notice.api.config.sms;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Config;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * study.daydayup.wolf.middleware.notice.api.config
 *
 * @author Wingle
 * @since 2020/3/20 3:07 下午
 **/
@Data
public class SMSSupplier implements Config {
    private boolean enable = true;
    private boolean useGlobal = false;
    private int order = 9999;

    private String signature;
    private String senderNum;
    private String brand;

    @NotBlank
    private String appId;
    @NotBlank
    private String appSecret;

    @NotBlank
    private String sendUrl;
    private String queryUrl;
    private String xxxUrl;

    private Map<String, String> templateMap;

}
