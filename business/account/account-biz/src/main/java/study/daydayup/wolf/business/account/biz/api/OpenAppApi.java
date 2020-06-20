package study.daydayup.wolf.business.account.biz.api;

import lombok.NonNull;
import study.daydayup.wolf.business.account.api.entity.OpenApp;
import study.daydayup.wolf.business.account.api.enums.OpenAppTypeEnum;
import study.daydayup.wolf.business.account.api.service.OpenAppService;
import study.daydayup.wolf.business.account.biz.converter.OpenAppConverter;
import study.daydayup.wolf.business.account.biz.dal.dao.OpenAppDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.OpenAppDO;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
        OpenApp app;
        OpenAppDO appDO = openAppDAO.selectByOrgIdAndAppType(orgId, appType);
        if (appDO != null) {
            app = OpenAppConverter.toModel(appDO);
        } else {
            app = initApp(orgId, appType);
        }

        return Result.ok(app);
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
    public Result<List<OpenApp>> findAll(@NonNull Long orgId) {
        List<OpenAppDO> appDOList = openAppDAO.selectByOrgId(orgId);
        List<OpenApp> appList = OpenAppConverter.toModel(appDOList);

        return Result.ok(appList);
    }

    @Override
    public Result<OpenApp> recreateApp(@NonNull Long orgId, @NonNull Integer appType) {
        OpenApp app;

        Long count = openAppDAO.countByOrgIdAndAppType(orgId, appType);
        if (null != count && count > 0) {
            app = modifyApp(orgId, appType);
        } else {
            app = initApp(orgId, appType);
        }

        return Result.ok(app);
    }

    private OpenApp modifyApp(@NonNull Long orgId, @NonNull Integer appType) {
        OpenApp app = createApp(orgId, appType);
        app.setUpdatedAt(LocalDateTime.now());

        OpenAppDO appDO = OpenAppConverter.toDO(app);
        openAppDAO.updateByOrgIdAndAppType(appDO, orgId, appType);

        return app;
    }

    private OpenApp initApp(@NonNull Long orgId, @NonNull Integer appType) {
        OpenApp app = createApp(orgId, appType);
        app.setCreatedAt(LocalDateTime.now());

        OpenAppDO appDO = OpenAppConverter.toDO(app);
        openAppDAO.insertSelective(appDO);

        return app;
    }

    private OpenApp createApp(@NonNull Long orgId, @NonNull Integer appType) {
        OpenApp app = OpenApp.builder()
                .orgId(orgId)
                .appType(appType)
                .build();

        app.setAppId(StringUtil.uuid());
        app.setAppSecret(StringUtil.uuid());

        return app;
    }
}
