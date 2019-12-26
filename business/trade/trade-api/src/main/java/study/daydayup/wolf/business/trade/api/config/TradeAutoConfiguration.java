package study.daydayup.wolf.business.trade.api.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.*;


/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:49 上午
 **/
@Configuration
public class TradeAutoConfiguration {

    @Bean("trade")
    @Scope("singleton")
    public EventBus eventBus() {
        return new EventBus("trade");
    }

}
