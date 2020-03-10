package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.service.CreditConfigService;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditConfigDAO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.api
 *
 * @author Wingle
 * @since 2020/3/10 1:19 下午
 **/
@RpcService(protocol = "dubbo")
public class CreditConfigServiceImpl implements CreditConfigService {
    @Resource
    private CreditConfigDAO dao;

    @Override
    public CreditConfig find(Long orgId) {
        return null;
    }

    @Override
    public int save(CreditConfig config) {
        return 0;
    }
}
