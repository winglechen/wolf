package study.daydayup.wolf.business.uc.agent.config;

import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import study.daydayup.wolf.business.uc.agent.setting.AccountStatusAgent;
import study.daydayup.wolf.business.uc.agent.setting.CustomerStatusAgent;


/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:49 上午
 **/
@Configuration
@ComponentScan("study.daydayup.wolf.business.uc.agent")
public class UcAutoConfiguration {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CustomerStatusAgent customerStatusAgent() {
        CustomerStatusAgent agent = new CustomerStatusAgent();

        return agent;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccountStatusAgent accountStatusAgent() {
        AccountStatusAgent agent = new AccountStatusAgent();

        return agent;
    }


}
