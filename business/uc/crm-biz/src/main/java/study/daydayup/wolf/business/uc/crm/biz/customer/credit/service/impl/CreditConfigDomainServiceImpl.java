package study.daydayup.wolf.business.uc.crm.biz.customer.credit.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditConfigConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditConfigDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditConfigDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.service.CreditConfigDomainService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.service.impl
 *
 * @author Wingle
 * @since 2020/3/10 6:52 下午
 **/
@Component
public class CreditConfigDomainServiceImpl implements CreditConfigDomainService {
    @Resource
    private CreditConfigDAO dao;

    @Override
    public CreditConfig find(@NonNull Long orgId) {
        CreditConfigDO configDO = dao.selectByOrgId(orgId);
        if (configDO == null) {
            return initConfig(orgId);
        }

        return CreditConfigConverter.toModel(configDO);
    }

    private CreditConfig initConfig(@NonNull Long orgId) {
        CreditConfig config = new CreditConfig();
        config.setOrgId(orgId);

        return config;
    }
}
