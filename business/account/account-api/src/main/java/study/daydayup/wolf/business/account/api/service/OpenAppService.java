package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.entity.OpenApp;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

import java.util.List;

/**
 * study.daydayup.wolf.business.account.api.service
 *
 * @author Wingle
 * @since 2020/6/20 2:28 下午
 **/
public interface OpenAppService extends Service {
    Result<OpenApp> find(Long orgId, Integer appType);
    Result<OpenApp> findTestApp(Long orgId);
    Result<OpenApp> findProductionApp(Long orgId);
    Result<List<OpenApp>> findAll(Long orgId);

    Result<OpenApp> recreateApp(Long orgId, Integer appType);
}
