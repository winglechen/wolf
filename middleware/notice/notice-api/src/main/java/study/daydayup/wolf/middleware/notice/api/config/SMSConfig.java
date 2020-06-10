package study.daydayup.wolf.middleware.notice.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.common.model.type.Bool;
import study.daydayup.wolf.middleware.notice.api.config.sms.SMSSupplier;
import study.daydayup.wolf.framework.layer.api.Config;

import javax.validation.constraints.NotNull;
import java.util.Map;


/**
 * study.daydayup.wolf.middleware.notice.api.config
 *
 * @author Wingle
 * @since 2020/3/20 3:03 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.sms")
public class SMSConfig implements Config {
    public static final String SMS_NAMESPACE = "sms";
    public static final String BRAND_PLACEHOLDER = "@BRAND@";

    private boolean useGlobal;
    private boolean mockMode;
    private String defaultSupplier;

    private String senderNum;
    private String signature;
    private String brand;

    @NotNull
    private Map<String, SMSSupplier> supplier;

}
