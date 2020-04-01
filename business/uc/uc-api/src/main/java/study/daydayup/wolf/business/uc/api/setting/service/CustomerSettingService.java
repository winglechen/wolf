package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.entity.CustomerSetting;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface CustomerSettingService extends Service {
    Result<CustomerSetting> find(Long accountId, Long orgId);
    Result<Integer> save(CustomerSetting customerSetting);
}
