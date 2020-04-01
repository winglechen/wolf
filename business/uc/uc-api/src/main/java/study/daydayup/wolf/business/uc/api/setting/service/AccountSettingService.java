package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.entity.AccountSetting;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface AccountSettingService extends Service {
    Result<AccountSetting> find(Long accountId);
    Result<Integer> save(AccountSetting accountSetting);
}
