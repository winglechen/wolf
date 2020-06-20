package study.daydayup.wolf.business.account.biz.api;

import lombok.NonNull;
import study.daydayup.wolf.business.account.api.entity.OpenApp;
import study.daydayup.wolf.business.account.api.enums.OpenAppTypeEnum;
import study.daydayup.wolf.business.account.api.service.OpenAppService;
import study.daydayup.wolf.business.account.biz.dal.dao.OpenAppDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.OpenAppDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2020/6/20 4:27 下午
 **/
@RpcService
public class OpenAppApi implements OpenAppService {
    @Resource
    private OpenAppDAO openAppDAO;
    @Override
    public Result<OpenApp> find(@NonNull Long orgId, @NonNull Integer appType) {
        OpenAppDO appDO = openAppDAO.selectByOrgIdAndAppType(orgId, appType);
        return null;
    }

    @Override
    public Result<OpenApp> findTestApp(Long orgId) {
        return find(orgId, OpenAppTypeEnum.TEST.getCode());
    }

    @Override
    public Result<OpenApp> findProductionApp(Long orgId) {
        return find(orgId, OpenAppTypeEnum.PRODUCTION.getCode());
    }

    @Override
    public Result<List<OpenApp>> findAll(Long orgId) {
        return null;
    }

    @Override
    public Result<OpenApp> recreateApp(Long orgId, Integer appType) {
        return null;
    }
}
