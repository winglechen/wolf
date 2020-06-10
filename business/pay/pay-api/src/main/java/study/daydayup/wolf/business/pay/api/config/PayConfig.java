package study.daydayup.wolf.business.pay.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.api.config
 *
 * @author Wingle
 * @since 2020/4/26 10:51 下午
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "wolf.pay")
public class PayConfig {
    public static final String PAY_NAMESPACE = "pay";

    private boolean useGlobal;
    private String defaultSupplier;

    private String returnUrl;

    @NotNull
    private Map<String, PaySupplier> supplier;


}
