package study.daydayup.wolf.business.account.biz.api;

import study.daydayup.wolf.business.account.api.entity.Organization;
import study.daydayup.wolf.business.account.api.service.OrganizationService;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 7:19 下午
 **/
@RpcService(protocol = "dubbo")
public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public long create(Organization org) {
        return 0;
    }

    @Override
    public Organization findById(long orgId) {
        return null;
    }

    @Override
    public Organization findByName(String name) {
        return null;
    }

    @Override
    public void removeById(long orgId) {

    }

    @Override
    public void modifyById(Organization org) {

    }
}
