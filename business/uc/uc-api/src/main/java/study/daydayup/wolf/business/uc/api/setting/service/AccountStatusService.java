package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.entity.AccountStatus;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface AccountStatusService extends Service {
    AccountStatus find(Long accountId);
    void save(AccountStatus accountStatus);
}
