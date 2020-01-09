package study.daydayup.wolf.business.uc.agent.config;

import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.agent.setting.AccountStatusAgent;
import study.daydayup.wolf.business.uc.agent.setting.CustomerStatusAgent;

import javax.annotation.Resource;


/**
 * study.daydayup.wolf.business.account.auth.agent.config
 *
 * @author Wingle
 * @since 2019/12/5 9:49 上午
 **/
@Configuration
@ComponentScan("study.daydayup.wolf.business.uc.agent")
public class UcAutoConfiguration {
    @Resource
    private Session session;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CustomerStatusAgent customerStatusAgent() {
        CustomerStatusAgent agent = new CustomerStatusAgent();

        if (session.isLogin()) {
            Long accountId = session.get("accountId", Long.class);
            Long orgId = session.get("orgId", Long.class);
            agent.init(accountId, orgId);
        }

        return agent;
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccountStatusAgent accountStatusAgent() {
        AccountStatusAgent agent = new AccountStatusAgent();

        Long accountId = session.get("accountId", Long.class, false);
        if (null != accountId) {
            agent.init(accountId);
        }

        return agent;
    }


}
