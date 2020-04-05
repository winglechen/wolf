package study.daydayup.wolf.demo.my.stater.web.flow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * study.daydayup.wolf.demo.my.stater.web.flow
 *
 * @author Wingle
 * @since 2019/12/26 12:15 上午
 **/
@Configuration
public class FlowFactory {

    @Bean
    public TradeFlow create(TradeType tradeType) {
        if (1 == tradeType.type) {
            return new ContractFlow();
        }

        return new OrderFlow();
    }
}
