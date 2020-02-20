package study.daydayup.wolf.business.uc.agent.setting;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;
import study.daydayup.wolf.business.uc.api.setting.service.AccountStatusService;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
public class AccountStatusAgent {
    private boolean isInit = false;

    @Reference
    private AccountStatusService service;

    public void init(long accountId) {


        this.isInit = true;
    }

    public Boolean get(StatusEnum status) {
        return null;
    }

    public void set(StatusEnum status, boolean state) {

    }

}
