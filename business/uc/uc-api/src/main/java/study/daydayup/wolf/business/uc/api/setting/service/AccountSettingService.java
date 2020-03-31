package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.entity.AccountSetting;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface AccountSettingService extends Service {
    AccountSetting find(Long accountId);
    void save(AccountSetting accountSetting);
}
