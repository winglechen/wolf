package study.daydayup.wolf.business.pay.api.config;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.api.config
 *
 * @author Wingle
 * @since 2020/4/26 10:51 下午
 **/
public class PayConfig {
    @NotNull
    private Map<String, PaySupplier> supplier;
}
